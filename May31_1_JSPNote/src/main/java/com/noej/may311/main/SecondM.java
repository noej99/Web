package com.noej.may311.main;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecondM {
	public static void test(HttpServletRequest req, HttpServletResponse res) {
		// ~~~~/파일명?변수명=값&변수명=값&...
		int q = Integer.parseInt(req.getParameter("q"));
		System.out.println(q);
		
		// request parameter
		//		front-end쪽에서 만들어진 값
		//		request 소속(GET방식 : 주소에, POST방식 내부적으로)
		//		String or String[]
		//		Java에서 : request.getParameter("파라메터변수명");
		//		EL로	 : ${param.파라메터변수명 }
		
		String w = "ㅋ";
		req.setAttribute("w", w);
		// request attribute
		//		back-end쪽에서 만들어진 값
		//		request소속
		//		Object
		//		Java에서 : request.getAttribute("어트리뷰트명");
		//		EL로	 : ${어트리뷰트명 }
		//				   ${어트리뷰트명.멤버변수명 }
		String ww = (String) req.getAttribute("w");
		System.out.println(ww);
		
		// session attribute
		// 		back-end쪽에서 만들어진 값
		//		session소속(사용자-서버 연결상태)
		//		-> 기본 30분(30분동안 요청같은걸 하면 갱신)
		//		-> 연결만 되어있다면 어디서든 사용가능
		//		-> 사용자 개인의 공간
		//		Object
		//		Java에서 : req.getSession.getAttribute("어트리뷰트명");
		//		EL로 	 : ${sessionScope.어트리뷰트명 }
		double f = 123.45;
		HttpSession hs = req.getSession();
		hs.setAttribute("f", f);
		hs.setMaxInactiveInterval(10); // 세션 유지시간 5초로
		
		// cookie
		//		back-end쪽에서 만들어진 값
		//		유지시간동안 사용자pc에 파일로 저장
		//		-> 브라우저를 끄든지, 컴퓨터를 재부팅하든지
		//		기본 유지시간 : 
		//		String
		//		Java에서 : ...ThirdM
		//		EL로	 : ${cookie.이름.value }
		String gg = "abcd";
		Cookie c = new Cookie("g", gg);
		c.setMaxAge(10);
		res.addCookie(c);
	}
}
