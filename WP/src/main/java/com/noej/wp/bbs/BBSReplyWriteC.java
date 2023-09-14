package com.noej.wp.bbs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noej.wp.main.TokenGenerator;
import com.noej.wp.member.MemberDAO;

@WebServlet("/BBSReplyWriteC")
public class BBSReplyWriteC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (MemberDAO.getMdao().isLogined(request)) {
			BBSDAO.getBdao().writeReply(request);
		}
		TokenGenerator.generate(request);
		request.setAttribute("contentPage", "bbs/detail.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
