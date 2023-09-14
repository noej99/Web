<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/bbs.css">
<script type="text/javascript" src="js/kwonValidChecker.js"></script>
<script type="text/javascript" src="js/may26check.js"></script>
<script type="text/javascript" src="js/may26move.js"></script>
</head>
<body>
	<table id="siteTitleArea">
		<tr>
			<td align="center">
				<table id="siteTitle">
					<tr>
						<td>404error</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center" id="siteMenu">
				<a href="HomeController">home</a>
				<a href="BBSController">게시판</a>
				<a href="DRController">자료실</a>
				<a href=""></a>
			</td>
		</tr>
	</table>
	<table id="siteContentArea">
		<tr>
			<td align="center">
			<jsp:include page="${contentPage }"></jsp:include>
			</td>
		</tr>
	</table>
	<div id="resultArea">${result }</div>
</body>
</html>