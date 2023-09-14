package com.noej.may25ctdb.db;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DBController")
public class DBController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	// 데이터 저장 - 명사
		// 메소드, 명령 - 동사
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBDAO.connectTest(request);
		request.setAttribute("contentPage", "db.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
