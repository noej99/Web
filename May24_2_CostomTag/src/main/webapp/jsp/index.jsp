<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- 
	(include����)
	css/js/�׸�����/... ����ΰ� .jsp����x
	c����(webapp����)����
	
	.jsp -> �������� �ٲ�� ����Ǵ�
 -->
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/calc.css">
<link rel="stylesheet" href="css/bb.css">
<script type="text/javascript" src="js/kwonValidChecker.js"></script>
<script type="text/javascript" src="js/may242check.js"></script>
</head>
<body>
	<table id="site">
		<tr>
			<td id="siteTitle">����</td>
		</tr>
		<tr>
			<td id="siteMenu">
				<a href="HomeController">Ȩ</a>			
				<a href="TestController">�׽�Ʈ</a>			
				<a href="CalcController">��Ģ����</a>	
				<a href="BBController">���ھ߱�</a>		
			</td>
		</tr>
		<tr>
			<td id="siteContent" align="center">
				<jsp:include page="${contentPage }"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>