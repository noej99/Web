package com.noej.may311.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// C를 왜 Servlet
//		요청받을줄 아는게 : Servlet, jsp
//		jsp는 get/post구분 못하고
//		자바소스 자동완성 별로
//		v쪽에 특화
@WebServlet("/FirstController") // Servlet 톰캣에 등록
public class FirstController extends HttpServlet {
	// 별 필요 없는데, 지우면 warning떠서
	private static final long serialVersionUID = 1L;
    
	// 톰켓이 켜지면 -> 등록된 서블릿들 자동으로 만드니
	public FirstController() {
		
	}
	
	// 클라이언트로부터 GET방식 요청 받으면
	// 주소 직접 타이핑
	// <a>
	// JavaScript의 location.href
	// <form> + <button>
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!request.getParameterNames().hasMoreElements()) {
			RequestDispatcher rd = request.getRequestDispatcher("jsp/firstV.jsp");
			rd.forward(request, response);
		}
		// request.getRequestDispatcher("jsp/firstV.jsp").forward(request, response);
	}

	// 클라이언트로부터 POST방식 요청 받으면
	// <form> + <button>
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
