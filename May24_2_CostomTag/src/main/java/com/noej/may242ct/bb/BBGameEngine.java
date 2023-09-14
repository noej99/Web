package com.noej.may242ct.bb;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

// 계산담당하는 M은 singleton인 경우가 대부분
public class BBGameEngine {
	private String gameAns;
	private int turn;

	private static final BBGameEngine BBGE = new BBGameEngine();

	public BBGameEngine() {
		// TODO Auto-generated constructor stub
	}

	public static BBGameEngine getBbge() {
		return BBGE;
	}

	public void pickAns() {
		// 중복없는 3자리 정수
		int ga = new Random().nextInt(976) + 12; // 12~987
		gameAns = String.format("%03d", ga); // 012~987
		if (gameAns.charAt(0) == gameAns.charAt(1) || gameAns.charAt(1) == gameAns.charAt(2)
				|| gameAns.charAt(2) == gameAns.charAt(0)) {
			pickAns();
		}
		System.out.println(gameAns);
		turn = 0;
	}

	public void judge(HttpServletRequest req) {
		turn++;
		String userAns = req.getParameter("no");
		int s = 0;
		int b = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (userAns.charAt(i) == gameAns.charAt(j)) {
					if (i == j) {
						s++;
					} else {
						b++;
					}
					break;
				}
			}
		}
		req.setAttribute("s", s);
		req.setAttribute("b", b);
		if (s == 3) {
			req.setAttribute("result", turn + "타자만에 삼진");
			pickAns();
		}
	}
}
