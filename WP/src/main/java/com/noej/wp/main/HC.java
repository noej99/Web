package com.noej.wp.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noej.wp.bbs.BBSDAO;
import com.noej.wp.member.MemberDAO;
import com.noej.wp.sns.SNSDAO;

@WebServlet("/HC")
public class HC extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public HC() {
		SNSDAO.getSdao().setAllMsgCount();
		BBSDAO.getBdao().setAllMsgCount();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO.getMdao().isLogined(request);
		//request.setAttribute("loginPage", "member/login.jsp");
		request.setAttribute("contentPage", "home.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
