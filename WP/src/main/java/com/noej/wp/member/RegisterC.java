package com.noej.wp.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noej.wp.main.DateManager;

@WebServlet("/RegisterC")
public class RegisterC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateManager.getCurYear(request);
		MemberDAO.getMdao().isLogined(request);
		//request.setAttribute("loginPage", "member/login.jsp");
		request.setAttribute("contentPage", "member/register.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}
	// 가입성공 : 홈으로
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (MemberDAO.getMdao().register(request)) {
			MemberDAO.getMdao().isLogined(request);
			//request.setAttribute("loginPage", "member/login.jsp");
			request.setAttribute("contentPage", "member/register.jsp");
		}
		MemberDAO.getMdao().isLogined(request);
		//request.setAttribute("loginPage", "member/login.jsp");
		request.setAttribute("contentPage", "home.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
