<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<c:forEach var="c" items="${coffee }">
			<tr>
				<td>${c.name }</td>
				<td>${c.price }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>