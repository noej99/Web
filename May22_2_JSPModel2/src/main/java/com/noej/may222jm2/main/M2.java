package com.noej.may222jm2.main;

import javax.servlet.http.HttpServletRequest;

// Model
// 실제 계산
// Java : back-end개발자 (+고객)
public class M2 {

	public static void calculate(HttpServletRequest request) {
		double xxx = Double.parseDouble(request.getParameter("X"));
		double yyy = Double.parseDouble(request.getParameter("y"));
		double a = xxx + yyy;
		double b = xxx - yyy;
		double c = xxx * yyy;
		double d = xxx / yyy;

		M3 m3 = new M3(a, b, c, d);
		request.setAttribute("m333", m3);
	}
}
