<%@page import="java.util.Date"%>
<%@page import="java.util.Random"%>
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
	//request parameter
	//GET��� : �ּҿ�
	//POST��� : ����������
	//String
	//request�Ҽ�
	//html/js���� ������� ��
	String qq = request.getParameter("q");
	int qqq = Integer.parseInt(qq);
	int www = Integer.parseInt(request.getParameter("w"));

	//request attribute
	//Object
	//request�Ҽ�
	//java���� ������� ��
	Random r = new Random();
	int eee = r.nextInt(10);
	request.setAttribute("e", eee);

	Date now = new Date();
	request.setAttribute("f", now);

	//second -> third�ڵ��̵�
	//1) redirect : �ܼ� ������
	//response.sendRedirect("third.jsp");

	//2) forward : �ַ�(�� ������ �Ǵ�)
	RequestDispatcher rd = request.getRequestDispatcher("third.jsp");
	rd.forward(request, response);

	//3) include : ��ġ�����Ұ�(������ ���� ����)
	//rd.include(request, response);
	%>
	<h1>second</h1>
	q��
	<%=qqq%>, w��<%=www%>
</body>
</html>