package com.noej.wp.sns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.noej.wp.member.Member;

import noej.db.manager.NoejDBManager;

public class SNSDAO {
	private int allMsgCount;
	private int MsgPerPage;
	private static final SNSDAO SDAO = new SNSDAO();

	public SNSDAO() {
		MsgPerPage = 10;
	}

	public static SNSDAO getSdao() {
		return SDAO;
	}

	// 글쓰기 : SNSWriteC?txt=ㅇㅇ
	// 새로고침 : 직전에 했던 요청 그대로 다시하기
	// 새로고침을 못하게
	// 마우스 우클릭 못하게, f5못누르게
	// 글쓸때마다 뭔가 달라질 수 있는거

	public void clearSearch(HttpServletRequest req) {
		req.getSession().setAttribute("search", null);
	}

	public void delete(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");
			int no = Integer.parseInt(req.getParameter("no"));
			String sql = "delete from wp_sns where ws_no = ?";
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
	
	public void deleteReply(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");
			int no = Integer.parseInt(req.getParameter("no"));
			String sql = "delete from wp_sns_reply where wsr_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "댓글삭제 성공");
			} else {
				req.setAttribute("result", "댓글삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "댓글삭제 실패");
		}
		NoejDBManager.close(con, pstmt, null);
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

			String sql = "select ws_no, wm_id, wm_photo, ws_txt, ws_date " + "from ( " + "	select * " + "	from ( "
					+ "		select rownum as rn, ws_no, ws_writer, ws_txt, ws_date " + "		from ( "
					+ "			select * " + "			from WP_SNS " + "         where ws_txt like '%'||?||'%'"
					+ "			order by ws_date desc " + "		) " + "	) " + "	where rn >= ? and rn <= ? " + "), ( "
					+ "	select wm_id, wm_photo " + "	from wp_member " + "	where wm_id in ( "
					+ "		select ws_writer " + "		from ( "
					+ "			select rownum as rn, ws_no, ws_writer, ws_txt, ws_date " + "			from ( "
					+ "				select * " + "				from WP_SNS "
					+ " 	   		where ws_txt like '%'||?||'%'" + "				order by ws_date desc "
					+ "			) " + "		) " + "		where rn >= ? and rn <= ? " + "	) " + ") "
					+ "where ws_writer = wm_id " + "order by ws_date desc";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			pstmt.setString(4, search);
			pstmt.setInt(5, start);
			pstmt.setInt(6, end);
			rs = pstmt.executeQuery();

			ArrayList<SNSMsg> msgs = new ArrayList<>();
			while (rs.next()) {
				msgs.add(new SNSMsg(rs.getInt("ws_no"), rs.getString("ws_txt"), rs.getDate("ws_date"),
						rs.getString("wm_id"), rs.getString("wm_photo")));
			}
			for (SNSMsg snsMsg : msgs) {
				snsMsg.setReplys(
						getReply(snsMsg.getNo())
						);
			}
			
			req.setAttribute("msgs", msgs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		NoejDBManager.close(con, pstmt, rs);
	}
	
	public ArrayList<SNSReply> getReply(int wsNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			
			String sql = "select * from wp_sns_reply where wsr_ws_no = ? order by wsr_date";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wsNo);
			rs = pstmt.executeQuery();
			
			ArrayList<SNSReply> replys = new ArrayList<>();
			while (rs.next()) {
				replys.add(
					new SNSReply(
						rs.getInt("wsr_no"),
						rs.getInt("wsr_ws_no"),
						rs.getString("wsr_writer"),
						rs.getString("wsr_txt"),
						rs.getDate("wsr_date")
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
	
	public int getMemberMsgCount(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			String sql = "select count(*) from wp_sns where ws_writer=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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

	public int getSearchMsgCount(String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			String sql = "select count(*) from wp_sns where ws_txt like '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
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

	public void search(HttpServletRequest req) {
		String search = req.getParameter("search");
		req.getSession().setAttribute("search", search);
	}

	public void setAllMsgCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			String sql = "select count(*) from wp_sns";
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

	public void setAllMsgCount(int MsgCount) {
		System.out.println(allMsgCount);
		System.out.println(MsgCount);
		allMsgCount -= MsgCount;
		System.out.println(allMsgCount);
	}

	public void update(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");

			int no = Integer.parseInt(req.getParameter("no"));
			String txt = req.getParameter("txt");

			String sql = "update wp_sns set ws_txt = ? where ws_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, txt);
			pstmt.setInt(2, no);

			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "글 수정 성공");
			} else {
				req.setAttribute("result", "글 수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글 수정 실패");
		}
		NoejDBManager.close(con, pstmt, null);
	}

	public void updateReply(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");

			int no = Integer.parseInt(req.getParameter("no"));
			String txt = req.getParameter("txt");

			String sql = "update wp_sns_reply set wsr_txt = ? where wsr_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, txt);
			pstmt.setInt(2, no);

			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "댓글 수정 성공");
			} else {
				req.setAttribute("result", "댓글 수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "댓글 수정 실패");
		}
		NoejDBManager.close(con, pstmt, null);
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
			String txt = req.getParameter("txt");
			txt = txt.replace("\r\n", "<br>");

			String sql = "insert into wp_sns values(wp_sns_seq.nextval, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, txt);

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
			int wsNo = Integer.parseInt(req.getParameter("ws_no"));
			String txt = req.getParameter("txt");

			String sql = "insert into wp_sns_reply values(wp_sns_reply_seq.nextval, ?, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wsNo);
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
