package com.noej.may26.dr;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import noej.db.manager.NoejDBManager;

public class DRDAO {
	private int allFileCount;
	private int filePerPage;
	private static final DRDAO DDAO = new DRDAO();

	public DRDAO() {
		filePerPage = 10;
	}

	public static DRDAO getDdao() {
		return DDAO;
	}

	public void setAllFileCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");

			String sql = "select count(*) from may30_dataroom";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			allFileCount = rs.getInt("count(*)");
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

			int pageCount = (int) Math.ceil(allFileCount / (double) filePerPage);
			req.setAttribute("pageCount", pageCount);

			int start = (page - 1) * filePerPage + 1;
			int end = filePerPage * page;

			String sql = "select * " + "from( " + "	select rownum as rn, d_no, d_title, d_date " + "	from( "
					+ "		select d_no, d_title, d_date " + "		from may30_dataroom "
					+ "		order by d_date desc " + "	) " + ") " + "where rn >= ? and rn <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			ArrayList<DRFile> files = new ArrayList<>();

			while (rs.next()) {
				files.add(new DRFile(rs.getInt("d_no"), rs.getString("d_title"), null, rs.getDate("d_date")));
			}
			req.setAttribute("files", files);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		NoejDBManager.close(con, pstmt, rs);
	}

	// 파일용량문제로 업로드 실패
	// DB서버문제로 실패
	public void upload(HttpServletRequest req) {
		MultipartRequest mr = null;
		String path = req.getSession().getServletContext().getRealPath("drFile");
		try {
			mr = new MultipartRequest(req, path, 10 * 1024 * 1024, "euc-kr", new DefaultFileRenamePolicy());

		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("result", "업로드 실패(파일)");
			return;
		}

		// DB작업
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");
			String title = mr.getParameter("title");
			// 톰캣이 한글로된 파일명 인식 못
			String file = URLEncoder.encode(mr.getFilesystemName("file"), "euc-kr").replace("+", " ");

			String sql = "insert into may30_dataroom values(may30_dataroom_seq.nextval, ?, ?, sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, file);

			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "업로드 성공");
				allFileCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 이미 파일이 업로드 되어있음 -> 파일지우기
			File f = new File(path + "/" + mr.getFilesystemName("file"));
			f.delete();
			req.setAttribute("result", "업로드 실패(DB)");
		}
		NoejDBManager.close(con, pstmt, null);
	}

	// 파일 수정하는 사람 : 새 파일이 용량이 커
	// 파일 수정 안하는 사람
	public boolean update(HttpServletRequest req) {
		MultipartRequest mr = null;
		String path = req.getSession().getServletContext().getRealPath("drFile");
		String oldFile = null;
		String newFile = null;
		try {
			mr = new MultipartRequest(req, path, 10 * 1024 * 1024, "euc-kr", new DefaultFileRenamePolicy());

		} catch (Exception e) {
			// TODO: handle exception
			req.setAttribute("result", "업로드 실패(파일)");
			return false;
		}
		// 파일 수정하는 사람(파일 업로드 끝난상태)
		// 파일 수정안하는 사람
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");
			int no = Integer.parseInt(mr.getParameter("no"));
			String title = mr.getParameter("title");
			oldFile = URLEncoder.encode(getFileName(no), "euc-kr").replace("+", " ");
			newFile = mr.getFilesystemName("file");
			if (newFile == null) {
				newFile = oldFile;
			} else {
				newFile = URLEncoder.encode(newFile, "euc-kr").replace("+", " ");
			}

			String sql = "update may3_dataroom set d_title=?, d_file=? where d_no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, newFile);
			pstmt.setInt(3, no);

			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "수정 성공");
				// 파일 바꾸는 사람 : 옛날 파일 지워야
				if (!oldFile.equals(newFile)) {
					oldFile = URLDecoder.decode(oldFile, "euc-kr");
					new File(path + "/" + oldFile).delete();
				}
				getDetail(no, req);
				return true;
			} else {
				req.setAttribute("result", "수정 실패");
				// 파일 바꾸는 사람 : 새 파일 지워야
				if (!oldFile.equals(newFile)) {
					newFile = URLDecoder.decode(newFile, "euc-kr");
					new File(path + "/" + newFile).delete();
				}
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "수정 실패");
			// 파일 바꾸는 사람 : 새 파일 지워야
			if (!oldFile.equals(newFile)) {
				try {
					newFile = URLDecoder.decode(newFile, "euc-kr");
				} catch (Exception e2) {
					// TODO: handle exception
				}
				new File(path + "/" + newFile).delete();
			}
			return false;
		} finally {
			NoejDBManager.close(con, pstmt, null);
		}
	}

	public boolean getDetail(int no, HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			
			String sql = "select * from may30_dataroom where d_no=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getFileName(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");

			String sql = "select d_file from may30_dataroom where d_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			rs.next();
			return URLDecoder.decode(rs.getString("d_file"), "euc-kr");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			NoejDBManager.close(con, pstmt, rs);
		}
	}

	public void delete(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = NoejDBManager.connect("noejPool");

			int no = Integer.parseInt(req.getParameter("no"));
			String fileName = getFileName(no);

			String sql = "delete from may30_dataroom where d_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "파일삭제 성공");
				allFileCount--;
				// 파일 삭제
				String folder = req.getSession().getServletContext().getRealPath("drFile");

				File f = new File(folder + "/" + fileName);
				f.delete();
			} else {
				req.setAttribute("result", "파일삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "파일삭제 실패");
		}
		NoejDBManager.close(con, pstmt, null);
	}
}
