<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function gogo() {
		var qqInput = document.getElementById("qq");
		var wwInput = document.getElementById("ww");
		alert(qqInput.value);
		// GET��� ��û
		location.href = "second.jsp?q=" +  qqInput.value + "&w=" + wwInput.value;
	}
</script>
</head>
<body>
	<%
	// request(�����̵�)
	// first -> second�� �̵�
	// 1) <a></a>
	//		text�κ� ������ GET��� ��û
	// 2) JavaScript
	//		�پ��� �̺�Ʈ GET��� ��û
	// 3) <form> + <button>
	//		��ư ������ GET/POST��� ��û
	%>
	<h1>first</h1>
	<a href="second.jsp?q=10&w=20">second��</a>
	<hr>
	<input id="qq"><br>
	<input id="ww"><br>
	<span ondblclick="gogo();">second��</span>
	<hr>
	<form action="second.jsp">
	<input name="q"><br>
	<input name="w"><br>
		<button>second��</button>
	</form>
</body>
</html>