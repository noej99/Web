package com.noej.may25ctdb.jstl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class DataMaker {
	public static void make(HttpServletRequest req) {
		int a = 123456789;
		req.setAttribute("a", a);
		
		double b = 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679;
		req.setAttribute("b", b);
		
		// String c = "20230525";
		Date c = new Date();
		req.setAttribute("c", c);
	}
}
