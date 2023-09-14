package com.noej.may26.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import noej.db.manager.NoejDBManager;

// textarea에서 엔터 : \r\n
// 웹에서 줄바꿈 : <br>

// 웹에서 쓸거면 \r\n -> <br> (replace)
// java/textarea에서 쓸거면 그대로
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

	public void setAllMsgCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			String sql = "select count(*) from may26_bbs";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			allMsgCount = rs.getInt("count(*)");
			System.out.println("전체 : " + allMsgCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		NoejDBManager.close(con, pstmt, rs);
	}

	public void get(int page, HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");

			int pageCount = (int) Math.ceil(allMsgCount / (double) MsgPerPage);
			req.setAttribute("pageCount", pageCount);

			int start = (page - 1) * MsgPerPage + 1;
			int end = MsgPerPage * page;

			String sql = "select * " + "from ( " + "select rownum as rn, b_no, b_title, b_date " + "from ( "
					+ "select b_no, b_title, b_date " + "from may26_bbs " + "order by b_date desc " + ") " + ") "
					+ "where rn >= ? and rn <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			ArrayList<BBSMsg> msgs = new ArrayList<>();
			BBSMsg bm = null;
			while (rs.next()) {
				bm = new BBSMsg();
				bm.setNo(rs.getInt("b_no"));
				bm.setTitle(rs.getString("b_title"));
				// bm.setTxt(rs.getString("b_txt"));
				bm.setDate(rs.getDate("b_date"));
				msgs.add(bm);
			}

			req.setAttribute("msgs", msgs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		NoejDBManager.close(con, pstmt, rs);
	}

	public void write(HttpServletRequest req) {
		// 사용이후 객체를 닫기위해 위로
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 연결
			con = NoejDBManager.connect("noejPool");

			// 데이터 확보
			req.setCharacterEncoding("euc-kr");
			String title = req.getParameter("title");
			String txt = req.getParameter("txt");

			// sql(미완성 : 값 들어갈 자리에 ?, ;빼고)
			String sql = "insert into MAY26_BBS " + "values(may26_bbs_seq.nextval, ?, ?, sysdate)";

			// 총괄매니저객체
			pstmt = con.prepareStatement(sql);

			// ? 채우기
			pstmt.setString(1, title);
			pstmt.setString(2, txt);

			// 실행
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "글쓰기 성공");
				allMsgCount++;
				System.out.println("쓰고 나면 : " + allMsgCount);
			}

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글쓰기 실패");
		}
		NoejDBManager.close(con, pstmt, null);
	}
	
	public boolean getDetail(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			int no = Integer.parseInt(req.getParameter("no"));
			
			String sql = "select * from may26_bbs where b_no=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				BBSMsg bm = new BBSMsg();
				bm.setNo(rs.getInt("b_no"));
				bm.setTitle(rs.getString("b_title"));
				bm.setTxt(rs.getString("b_txt"));
				bm.setDate(rs.getDate("b_date"));
				req.setAttribute("bm", bm);
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		} finally {
			NoejDBManager.close(con,pstmt,rs);
		}
	}
	
	public void delete(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");
			int no = Integer.parseInt(req.getParameter("no"));
			String sql = "delete from may26_bbs where b_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "글삭제 성공");
			}else {
				req.setAttribute("result", "글삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글삭제 실패");
		}
		NoejDBManager.close(con, pstmt, null);
	}
	
	public boolean update(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");
			req.setCharacterEncoding("euc-kr");
			int no = Integer.parseInt(req.getParameter("no"));
			String title = req.getParameter("title");
			String txt = req.getParameter("txt");
			
			String sql = "update may26_bbs set b_title = ?, b_txt = ? where b_no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, txt);
			pstmt.setInt(3, no);
			
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "글수정 성공");
				return true;
			}else {
				req.setAttribute("result", "글수정 실패");
				return false;
			}
		} catch (Exception e) {
			req.setAttribute("result", "글수정 실패");
			return false;
		} finally {
			NoejDBManager.close(con, pstmt, null);
		}
	}
}
