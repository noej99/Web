<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		// 프로젝트 만들고처음 한번만 실행
		// 소스 수정하고 저장하면 자동 재시작
		//		버그
		// 1) 파일 새로 만들었으면 실행을 다시하는게 좋더라
		// 2) 기다림
		// 3) shift + f5
	%>

	${a }
	<hr>
	<fmt:formatNumber value="${a }" type="number"/>
	<hr>
	<!-- 
		3자리마다,
		국가설정에 맞는 통화 기호
	-->
	<fmt:formatNumber value="${a }" type="currency" currencySymbol="\\"/>
	<hr>
	${b }
	<hr>
	<fmt:formatNumber value="${b }" type="percent"/>
	<hr>
	<!-- 
		유효숫자
		# : 있으면 있고 없으면 없고
		0 : 있든 말든 자리 채워
	 -->
	<fmt:formatNumber value="${b }" pattern="#.000"/>
	<hr>
	${c }
	<hr>
	<fmt:formatDate value="${c }" type="date" dateStyle="long"/><br>
	<fmt:formatDate value="${c }" type="date" dateStyle="short"/>
	<hr>
	<fmt:formatDate value="${c }" type="time" timeStyle="long"/>
	<fmt:formatDate value="${c }" type="time" timeStyle="short"/>
	<hr>
	<fmt:formatDate value="${c }" type="both" dateStyle="long" timeStyle="short"/>
	<hr>
	<fmt:formatDate value="${c }" pattern="E"/>
</body>
</html>