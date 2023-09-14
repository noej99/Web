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
		// ������Ʈ �����ó�� �ѹ��� ����
		// �ҽ� �����ϰ� �����ϸ� �ڵ� �����
		//		����
		// 1) ���� ���� ��������� ������ �ٽ��ϴ°� ������
		// 2) ��ٸ�
		// 3) shift + f5
	%>

	${a }
	<hr>
	<fmt:formatNumber value="${a }" type="number"/>
	<hr>
	<!-- 
		3�ڸ�����,
		���������� �´� ��ȭ ��ȣ
	-->
	<fmt:formatNumber value="${a }" type="currency" currencySymbol="\\"/>
	<hr>
	${b }
	<hr>
	<fmt:formatNumber value="${b }" type="percent"/>
	<hr>
	<!-- 
		��ȿ����
		# : ������ �ְ� ������ ����
		0 : �ֵ� ���� �ڸ� ä��
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