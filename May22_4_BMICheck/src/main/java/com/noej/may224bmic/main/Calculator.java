package com.noej.may224bmic.main;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class Calculator {

	public static void calculate(HttpServletRequest request) {
		try {

			MultipartRequest mr = new MultipartRequest(request,
					request.getSession().getServletContext().getRealPath("img"), 10485760, "euc-kr",
					new DefaultFileRenamePolicy());
			// 인코딩방식에 맞춰서
			String n = mr.getParameter("n");
			double h = Double.parseDouble(request.getParameter("h"));
			double w = Double.parseDouble(request.getParameter("w"));
			String p = URLEncoder.encode(mr.getFilesystemName("p"), "euc-kr".replace("+", " "));
			double bmi = w / (h * h);
			String result = "저체중";
			if (bmi >= 35) {
				result = "고도비만";
			} else if (bmi >= 30) {
				result = "중도비만";
			} else if (bmi >= 25) {
				result = "경도비만";
			} else if (bmi >= 23) {
				result = "과체중";
			} else if (bmi >= 18.5) {
				result = "정상";
			}
			
			Result r = new Result(n, h, w, p, bmi, result);
			request.setAttribute("r", r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
