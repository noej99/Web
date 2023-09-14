package com.noej.jun08tosojsp.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import noej.db.manager.NoejDBManager;

public class SnackDAO {
	
	private static final SnackDAO SDAO = new SnackDAO();
	
	private SnackDAO() {
	}
	
	public static SnackDAO getSdao() {
		return SDAO;
	}
	
	public String get() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			
			String sql = "select * from jun08_snack";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			boolean first = true;
			while (rs.next()) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				sb.append("{");
				sb.append("\"s_name\":\"" + rs.getString("js_name") +"\",");
				sb.append("\"s_price\":" + rs.getInt("js_price") + ",");
				sb.append("\"s_brand\":\"" + rs.getString("js_brand") + "\"");
				sb.append("}");
			}
			sb.append("]");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			NoejDBManager.close(con, pstmt, rs);
		}
	}
}
