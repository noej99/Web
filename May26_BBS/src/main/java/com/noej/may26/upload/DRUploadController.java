package com.noej.may26.upload;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noej.may26.dr.DRDAO;

@WebServlet("/DRUploadController")
public class DRUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("contentPage", "dr/upload.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DRDAO.getDdao().upload(request);
		DRDAO.getDdao().get(1, request);
		request.setAttribute("contentPage", "dr/dr.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
