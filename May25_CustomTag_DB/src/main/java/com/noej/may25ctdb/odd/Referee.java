package com.noej.may25ctdb.odd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class Referee {
	
	public static void judge(HttpServletRequest req) {
		int n = Integer.parseInt(req.getParameter("n"));
		
		String oe = (n % 2 == 1) ? "홀수" : "짝수";
		req.setAttribute("oeResult", oe);
		
		ArrayList<Menu> menus = new ArrayList<>();
		menus.add(new Menu("라면", 3500));
		menus.add(new Menu("김밥", 3000));
		menus.add(new Menu("순대국밥", 9000));
		menus.add(new Menu("짜장면", 6000));
		req.setAttribute("menus", menus);
	}
}
