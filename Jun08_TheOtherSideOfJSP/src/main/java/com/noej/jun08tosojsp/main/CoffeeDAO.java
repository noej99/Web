package com.noej.jun08tosojsp.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import noej.db.manager.NoejDBManager;

public class CoffeeDAO {
	
	private static final CoffeeDAO CDAO = new CoffeeDAO();
	
	private CoffeeDAO() {

	}
	
	public static CoffeeDAO getCdao() {
		return CDAO;
	}
	
	public void get(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			
			String sql = "select * from jun08_coffee";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<Coffee> coffees = new ArrayList<>();
			while (rs.next()) {
				coffees.add(
					new Coffee(
						rs.getString("jc_name"),
						rs.getInt("jc_price")
					)
				);
			}
			req.setAttribute("coffee", coffees);
		} catch (Exception e) {
			e.printStackTrace();
		}
		NoejDBManager.close(con, pstmt, rs);
	}
	
	// HTML
	// ------ 디자인, 움직임 부족
	// HTML + CSS +JavaScript
	// ------ 
	// 
	// ------ 
	// JSP
	// ------ 
	// JSP Model2
	// ------ V에 .java가?
	// JSP Model2 + EL + JSTL
	// ------ 유지보수
	// Spring : JSP Model2를 유지보수하기 좋게 만드는 방법론
	// eGovFramework : 전자정부프레임워크
	//		Spring + Maven + MyBatis
	
	// ArrayList<객체>로 만들어서
	// request attribute : request소속
	// request parameter : request소속
	// session attribute : 클-서 연결상태 소속
	// cookie : 클 컴퓨터 소속
	
	// 제 3자(웹브라우저 아님)에게 데이터 주려면
	// => 글자 한덩어리로 만들어서 응답
	// => 데이터 주는쪽과 받는쪽의 약속된 형식이 필요할것
	// => 국제표준형식
	//		데이터를 html모양 빌려서 표현 : XML(eXtended Markup Language)
	//			가독성이 나쁘지 않음
	//			=> 설정파일에 많이
	//		데이터를 JavaScript모양 빌려서 표현 : JSON(JavaScript Object Notation)
	//			JSON이 파싱하기 쉽고 용량 적고
	//			=> 대부분 데이터는 JSON으로 돌아다님
	
	
	// 빅데이터/AI 결과물은 JSON/XML로 만들
	public String get2(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = NoejDBManager.connect("noejPool");
			
			String sql = "select * from jun08_coffee";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<jun08_coffee>"); // <테이블명>
			while (rs.next()) {
				sb.append("<coffee>"); // <뭐>
												// <필드명>값</필드명>
				sb.append("<c_name>" + rs.getString("jc_name") + "</c_name>");
				sb.append("<c_price>" + rs.getInt("jc_price") + "</c_price>");
				sb.append("</coffee>"); // </뭐>
			}
			sb.append("</jun08_coffee>"); // </테이블명>
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			NoejDBManager.close(con, pstmt, rs);
		}
	}
}
