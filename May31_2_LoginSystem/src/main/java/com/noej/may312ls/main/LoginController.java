package com.noej.may312ls.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.login(request, response);
		if (MemberDAO.isLogined(request)) {
			request.getRequestDispatcher("jsp/home.jsp").forward(request, response);	
		} else {
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
		}
		
	}
}
