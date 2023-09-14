package com.noej.may25ctdb.db;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import noej.db.manager.NoejDBManager;

// 튜브 : Connection
// 튜브대여소 : DataSource

// JDBC : 누군가가 연결요청을 하면 그때부터 세팅...
//		-> 느림
// ConnectionPool : 연결객체를 미리 만들어놨다가
//					누군가가 연결요청을 하면 그거 주기
//		-> 빠름

// 개발자PC
//		다양한 프로젝트

public class DBDAO {
	public static void connectTest(HttpServletRequest req) {
		Connection con = null;
		try {
			con = NoejDBManager.connect("noejPool");
			req.setAttribute("result", "연결성공");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "연결실패");
		}
		NoejDBManager.close(con, null, null);
	}
	
	public static void connectTest2(HttpServletRequest req) {
		// context.xml
		// 톰캣 설정파일
		// 거기다 객체 만들어 놓으면, 불러다 사용 가능
		// maxTotal : 최대 접속자 수
		// maxIdle : 만들어놓는 개수
		// Servers/context.xml
		// 모든 프로젝트에 다
		// 프로젝트/.../META-INF/context.xml
		// 그 프로젝트에서만
		Connection con = null;
		try {
			Context ctx = new InitialContext(); // context.xml
												// java:comp/env/이름
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/noejPool");

			con = ds.getConnection();

			req.setAttribute("result", "연결 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "연결 실패");
		}
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void connectTestOld(HttpServletRequest req) {
		Connection con = null;
		try {
			// DB메이커 다양 -> 최종적으로 연결할때 형식이 다 다르고
			// -> DB메이커별로 드라이버를

			// DB메이커별로 주소형식이 다 다르고
			// -> 주소만 봐도 드라이버 알아서 찾는데[쌩 java 콘솔 프로그램때]
			// -> .jsp프로젝트에서는 알아서 못찾음
			// 미리 주소를 찾아놓는 ConnectionPool
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@195.168.9.61:1521:xe";
			con = DriverManager.getConnection(url, "noej1", "j2527303");
			req.setAttribute("result", "connect");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "disconnect");
		}
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
