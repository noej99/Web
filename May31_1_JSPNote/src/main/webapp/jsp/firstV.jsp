<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function goSecondV() {
		location.href = "SecondController";
	}
</script>
</head>
<body>
<%
	// request
	// 1) <a href="��û�ּ�">����</a>
	//		���⸦ ������ ��û�ּ������� GET��� ��û
	// 2) JavaScript�� location.href = "��û�ּ�";
	//		???�� �ϸ� ��û�ּ������� GET��� ��û
	// 3) <form action="��û�ּ�"> + <button>
	//		input���� ���� or ��ư ������ ��û�ּ������� GET/POST��� ��û
	
	// ~~~~/���ϸ�?������=��&������=��&... ��û �Ķ����
%>

	<h1>firstV</h1>
	<a href="SecondController?q=10">secondV</a>
	<hr>
	<span ondblclick="goSecondV();">secondV</span>
	<hr>
	<form action="SecondController">
		<input name="q"><br>
		<button>secondV</button>
	</form>
</body>
</html>