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
	// ��Ű�� �ѱ� ���� �ȵƾ���
	//		�� -> %2A�� �ٲ�� �����߾���
	// �����ڵ��� �����Ƽ� ID�� �ѱ� �������߾��µ�
	// -> ����
%>
	<form action="LoginController" method="post">
		id : <input name="id" value="${cookie.lastLoginedID.value }"><p>
		pw : <input name="pw" type="password"><p>
		<button>�α���</button>
	</form>
</body>
</html>