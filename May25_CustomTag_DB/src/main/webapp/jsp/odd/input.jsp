<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form name="oddForm" action="OddController" onsubmit="return oCheck();">
		<input class="oInput" name="n" placeholder="����" autocomplete="off">
		<p>
			<button class="oBtn">Ȯ��</button>
	</form>
</body>
</html>