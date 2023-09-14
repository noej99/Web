package com.noej.may311.main;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ThirdM {
	public static void test(HttpServletRequest req) {
		String e = req.getParameter("e");
		System.out.println(e);
		
//		int q = Integer.parseInt(req.getParameter("q"));
//		System.out.println(q);
//		
//		String ww = (String) req.getAttribute("w");
//		System.out.println(ww);
		
		// double f = (Double) req.getSession().getAttribute("f");
		// System.out.println(f);
		
		// 지금 만들어져있는 쿠키들
//		Cookie[] cookies = req.getCookies();
//		for (Cookie c : cookies) {
//			if (c.getName().equals("g")) {
//				System.out.println(c.getValue());
//			}
//		}
	}
}
