<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/oInput.css">
<script type="text/javascript" src="js/kwonValidChecker.js"></script>
<script type="text/javascript" src="js/may25Check.js"></script>
</head>
<body>
	<table id="allTable">
		<tr>
			<td>
				<table id="siteTitleArea">
					<tr>
						<td>
							<table>
								<tr>
									<td>제목</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table id="siteMenuArea">
					<tr>
						<td><a href="HomeController">home</a></td>
						<td><a href="OddController">JSTL-core</a></td>
						<td><a href="JSTLFController">JSTL-formatting</a></td>
						<td><a href="DBController">DB</a></td>
						<td><a href="">수단</a></td>
					</tr>
				</table>
				<table>
					<tr>
						<td id="siteContentArea" align="center">
						<jsp:include page="${contentPage }"></jsp:include></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>