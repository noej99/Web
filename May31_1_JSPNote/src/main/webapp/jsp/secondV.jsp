<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>secondV</h1>
	${param.q }
	<hr>
	${w }<hr>
	<form action="ThirdController" method="post">
		<input name="e"><br>
		<button>thirdV</button>
	</form>
</body>
</html>