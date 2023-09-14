<%@page import="com.noej.may224bmic.main.Result"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		Result r = (Result) request.getAttribute("r");
	%>
	<table>
	<tr>
	<th><%=r.getName() %></th>
	</tr>
	<tr>
	<td align="center">
	<img src="img/<%=r.getPhoto() %>">
	</td>
	</tr>
	<tr>
	<td><%=r.getHeight() %></td>
	</tr>
	<tr>
	<td><%=r.getWeight() %></td>
	</tr>
	<tr>
	<td><%=r.getBmi() %></td>
	</tr>
	<tr>
	<td align="center"><%=r.getResult() %></td>
	</tr>
	</table>
</body>
</html>