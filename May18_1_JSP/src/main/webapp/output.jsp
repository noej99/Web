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
		String n = request.getParameter("n");
		int a = Integer.parseInt(request.getParameter("a"));
		String say = (a >= 20) ? "�������" : "����"; 
	%>
	
	<h1><%=n %>��</h1>
	<h2><%=say %></h2>
</body>
</html>









