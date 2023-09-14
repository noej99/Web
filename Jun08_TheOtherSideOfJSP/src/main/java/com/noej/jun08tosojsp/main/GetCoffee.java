package com.noej.jun08tosojsp.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetCoffee")
public class GetCoffee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인터넷 : 요청했을때 웹사이트(html)나오는거
		
		response.addHeader("Content-Type", "application/xml; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		String coffeeData = CoffeeDAO.getCdao().get2(request);
		pw.print(coffeeData);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
