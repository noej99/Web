package com.noej.wp.member;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import com.noej.wp.sns.SNSDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import noej.db.manager.NoejDBManager;

public class MemberDAO {
	private static final MemberDAO MDAO = new MemberDAO();

	private MemberDAO() {
	}

	public static MemberDAO getMdao() {
		return MDAO;
	}

	public boolean register(HttpServletRequest req) {
		MultipartRequest mr = null;
		String path = req.getSession().getServletContext().getRealPath("img");
		try {
			mr = new MultipartRequest(req, path, 10 * 1024 * 1024, "euc-kr", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "가입 실패(파일)");
			return false;
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");
			String id = mr.getParameter("id");
			String pw = mr.getParameter("pw");
			String name = mr.getParameter("name");
			String y = mr.getParameter("y");
			String m = mr.getParameter("m");
			int mm = Integer.parseInt(m);
			String d = mr.getParameter("d");
			int dd = Integer.parseInt(d);
			String birth = String.format("%s%02d%02d", y, mm, dd);
			String addr1 = mr.getParameter("addr1");
			String addr2 = mr.getParameter("addr2");
			String addr3 = mr.getParameter("addr3");
			String addr = addr2 + "!" + addr3 + "!" + addr1;
			// addr.split("!");
			String photo = mr.getFilesystemName("photo");
			photo = URLEncoder.encode(photo, "euc-kr");
			photo = photo.replace("+", " ");

			String sql = "insert into wp_member values(?, ?, ?, to_date(?, 'YYYYMMDD'), ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, addr);
			pstmt.setString(6, photo);

			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "가입 성공");
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "가입 실패(DB)");
			// 이미 올라가있을 프사파일 삭제
			new File(path + "/" + mr.getFilesystemName("photo")).delete();
			return false;
		} finally {
			NoejDBManager.close(con, pstmt, null);
		}
	}

	public void login(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			req.setCharacterEncoding("euc-kr");
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");

			String sql = "select * from wp_member where wm_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			// 자동완성아니고 직접 타이핑하는 부분
			// 어딜보고 쓰는건지
			if (rs.next()) {
				String dbPw = rs.getString("wm_pw");
				if (pw.equals(dbPw)) {
					Member m = new Member(id, pw, rs.getString("wm_name"), rs.getDate("wm_birth"),
							rs.getString("wm_addr"), rs.getString("wm_photo"));
					req.getSession().setAttribute("loginMember", m);
					req.getSession().setMaxInactiveInterval(1800);
					req.setAttribute("result", "로그인 성공");
				} else {
					req.setAttribute("result", "로그인 실패(pw)");
				}
			} else {
				req.setAttribute("result", "로그인 실패(미가입)");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "로그인 실패(DB)");
		}
		NoejDBManager.close(con, pstmt, rs);
	}
	
	public boolean isLogined(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if (m != null) {
			req.setAttribute("loginPage", "member/logined.jsp");
			return true;
		} else {
			req.setAttribute("loginPage", "member/login.jsp");
			return false;
		}
	}
	
	public void logout(HttpServletRequest req) {
		// 세션 끊기
		// 로그인 정보말고 세션에 넣어놓은 다른 것들도 다 날아갈테니
		// req.getSession().setMaxInactiveInterval(-1);
		req.getSession().setAttribute("loginMember", null);
	}
	
	public void bye(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {	
			con = NoejDBManager.connect("noejPool");
			Member m = (Member) req.getSession().getAttribute("loginMember");
			String id = m.getId();
			int msgCount = SNSDAO.getSdao().getMemberMsgCount(id);
			
			String sql = "delete from wp_member where wm_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "탈퇴성공");
				String path = req.getSession().getServletContext().getRealPath("img");
				String photo = URLDecoder.decode(m.getPhoto(), "euc-kr");
				new File(path + "/" + photo).delete();
				
				// 탈퇴했을때 뭐 정리
				// 그 사람이 쓴 글 수만큼 SNSDAO의 allMsgCount까기
				SNSDAO.getSdao().setAllMsgCount(msgCount);
			} else {
				req.setAttribute("result", "탈퇴실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "탈퇴실패(DB)");
		}
		NoejDBManager.close(con, pstmt, null);
	}
	
	public void splitAddr(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		String addr = m.getAddr();
		String[] addrr = addr.split("!");
		req.setAttribute("addr1", addrr[2]);
		req.setAttribute("addr2", addrr[0]);
		req.setAttribute("addr3", addrr[1]);
	}
	
	public void update(HttpServletRequest req) {
		MultipartRequest mr = null;
		String path = req.getSession().getServletContext().getRealPath("img");
		try {
			mr = new MultipartRequest(req, path, 10 * 1024 * 1024, "euc-kr", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "수정 실패(파일)");
			return;
		}
		// 프사 수정 안하거나
		// 프사 수정, 정상적으로 새프사 업로드 완료
			Connection con = null;
			PreparedStatement pstmt = null;
			String oldFile = null;
			String newFile = null;
		try {
			con = NoejDBManager.connect("noejPool");
			Member m = (Member) req.getSession().getAttribute("loginMember");
			oldFile = m.getPhoto();
			newFile = mr.getFilesystemName("photo");
			if (newFile == null) {
				newFile = oldFile;
			} else {
				newFile = URLEncoder.encode(newFile, "euc-kr").replace("+", " ");
			}
			String id = m.getId();
			String pw = mr.getParameter("pw");
			String name = mr.getParameter("name");
			String addr1 = mr.getParameter("addr1");
			String addr2 = mr.getParameter("addr2");
			String addr3 = mr.getParameter("addr3");
			String addr = addr2 + "!" + addr3 + "!" + addr1;
			
			String sql = "update wp_member "
					+ "set wm_pw = ?, wm_name = ?, wm_addr = ?, wm_photo = ? "
					+ "where wm_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, addr);
			pstmt.setString(4, newFile);
			pstmt.setString(5, id);
			
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "수정 성공");
				if (!oldFile.equals(newFile)) {
					oldFile = URLDecoder.decode(oldFile, "euc-kr");
					new File( path + "/" + oldFile).delete();
				}
				m.setPw(pw);
				m.setName(name);
				m.setAddr(addr);
				m.setPhoto(newFile);
				req.getSession().setAttribute("loginMember", m);
			} else {
				req.setAttribute("result", "수정 실패");
				if (!oldFile.equals(newFile)) {
					newFile = URLDecoder.decode(newFile, "euc-kr");
					new File( path + "/" + newFile).delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "수정 실패(DB)");
			if (!oldFile.equals(newFile)) {
				try {
					newFile = URLDecoder.decode(newFile, "euc-kr");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				new File( path + "/" + newFile).delete();
			}
		}
		NoejDBManager.close(con, pstmt, null);
	}
}
