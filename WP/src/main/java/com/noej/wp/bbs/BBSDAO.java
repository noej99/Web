package com.noej.wp.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.noej.wp.member.Member;
import com.noej.wp.sns.SNSReply;

import noej.db.manager.NoejDBManager;

public class BBSDAO {
	private int allMsgCount;
	private int MsgPerPage;

	private static final BBSDAO BDAO = new BBSDAO();

	private BBSDAO() {
		MsgPerPage = 10;
	}

	public static BBSDAO getBdao() {
		return BDAO;
	}

	public void get(int page, HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			String search = (String) req.getSession().getAttribute("search");
			int msgCount = allMsgCount;
			if (search == null) {
				search = "";
			} else {
				msgCount = getSearchMsgCount(search);
			}
			
			int pageCount = (int) Math.ceil(msgCount / (double) MsgPerPage);
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("page", page);

			int start = (page - 1) * MsgPerPage + 1;
			int end = MsgPerPage * page;

			String sql = 
			"select * "
			+ "from (   "
			+ "	select rownum as rn, wb_no, wb_title, wb_writer, wb_date  "
			+ "	from (   "
			+ "		select *   "
			+ "		from Wp_bbs   "
			+ "		where wb_title like '%'||?||'%' or wb_txt like '%'||?||'%'  "
			+ "		order by wb_date desc "
			+ "	)  "
			+ ")   "
			+ "where rn >= ? and rn <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rs = pstmt.executeQuery();

			ArrayList<BBSMsg> msgs = new ArrayList<>();
			BBSMsg bm = null;
			while (rs.next()) {
				bm = new BBSMsg();
				bm.setNo(rs.getInt("wb_no"));
				bm.setTitle(rs.getString("wb_title"));
				bm.setWriter(rs.getString("wb_writer"));
				bm.setDate(rs.getDate("wb_date"));
				msgs.add(bm);
			}
			for (BBSMsg bbsMsg : msgs) {
				bbsMsg.setReplys(
						getReply(bbsMsg.getNo())
						);
			}
			
			req.setAttribute("msgs", msgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		NoejDBManager.close(con, pstmt, rs);
	}

	public void write(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String token = req.getParameter("token");
			String lastToken = (String) req.getSession().getAttribute("lastToken");
			if (lastToken != null && token.equals(lastToken)) {
				req.setAttribute("result", "글쓰기 실패(새로고침)");
				return;
			}

			con = NoejDBManager.connect("noejPool");
			Member m = (Member) req.getSession().getAttribute("loginMember");
			String writer = m.getId();
			String title = req.getParameter("title");
			String txt = req.getParameter("txt");
			txt = txt.replace("\r\n", "<br>");

			String sql = "insert into wp_bbs values(wp_bbs_seq.nextval, ?, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, txt);

			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "글쓰기 성공");
				allMsgCount++;
				req.getSession().setAttribute("lastToken", token);
			}

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글쓰기 실패");
		}
		NoejDBManager.close(con, pstmt, null);
	}

	public void setAllMsgCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");

			String sql = "select count(*) from wp_bbs";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			allMsgCount = rs.getInt("count(*)");
			System.out.println(allMsgCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		NoejDBManager.close(con, pstmt, rs);
	}

	public void detail(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			int no = Integer.parseInt(req.getParameter("no"));

			String sql = "select * from wp_bbs where wb_no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			ArrayList<BBSMsg> bmsgs = new ArrayList<>();
			while (rs.next()) {
				bmsgs.add(new BBSMsg(rs.getInt("wb_no"), rs.getString("wb_writer"), rs.getString("wb_title"),
						rs.getString("wb_txt"), rs.getDate("Wb_date")));
			}
			req.setAttribute("bmsgs", bmsgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		NoejDBManager.close(con, pstmt, rs);
	}

	public void delete(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");
			int no = Integer.parseInt(req.getParameter("no"));
			String sql = "delete from wp_bbs where wb_no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "글삭제 성공");
				allMsgCount--;
			} else {
				req.setAttribute("result", "글삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글삭제 실패");
		}
		NoejDBManager.close(con, pstmt, null);
	}

	public void update(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");
			int no = Integer.parseInt(req.getParameter("no"));
			
			String title = req.getParameter("title");
			String txt = req.getParameter("txt");
			String sql = "update wp_bbs set wb_title = ?, wb_txt = ? where wb_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, txt);
			pstmt.setInt(3, no);

			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "글수정 성공");
			} else {
				req.setAttribute("result", "글수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글수정 실패");
		}
		NoejDBManager.close(con, pstmt, null);
	}

	public void search(HttpServletRequest req) {
		String search = req.getParameter("search");
		req.getSession().setAttribute("search", search);
	}

	public int getSearchMsgCount(String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			String sql = "select count(*) from wp_bbs where wb_title like '%'||?||'%' or wb_txt like '%'||?||'%' ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			NoejDBManager.close(con, pstmt, rs);
		}
	}

	public void clearSearch(HttpServletRequest req) {
		req.getSession().setAttribute("search", null);
	}

	public ArrayList<BBSReply> getReply (int wbNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
		
			String sql = "select * from wp_bbs_reply where wbr_wb_no = ? order by wbr_date";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wbNo);
			rs = pstmt.executeQuery();
			
			ArrayList<BBSReply> replys = new ArrayList<>();
			while (rs.next()) {
				replys.add(
						new BBSReply(
						rs.getInt("wbr_no"),
						rs.getInt("wbr_wb_no"),
						rs.getString("wbr_writer"),
						rs.getString("wbr_txt"),
						rs.getDate("wbr_date")
						)
					);
			}
			return replys;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			NoejDBManager.close(con, pstmt, rs);
		}
	}

	public void writeReply(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String token = req.getParameter("token");
			String lastToken = (String) req.getSession().getAttribute("lastToken");
			if (lastToken != null && token.equals(lastToken)) {
				req.setAttribute("result", "댓글쓰기 실패");
				return;
			}

			con = NoejDBManager.connect("noejPool");

			Member m = (Member) req.getSession().getAttribute("loginMember");
			String id = m.getId();
			int wbNo = Integer.parseInt(req.getParameter("no"));
			String txt = req.getParameter("txt");

			String sql = "insert into wp_bbs_reply values(wp_bbs_reply_seq.nextval, ?, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wbNo);
			pstmt.setString(2, id);
			pstmt.setString(3, txt);

			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "댓글쓰기 성공");
				req.getSession().setAttribute("lastToken", token);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "댓글쓰기 실패");
		}
		NoejDBManager.close(con, pstmt, null);
	}
}
