<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>숫자야구</title>
<link rel="stylesheet" href="css/bb.css">
<script type="js/kwonValidChecker.js"></script>
<script type="js/bbChecker.js"></script>
</head>
<body>
	<form action="Controller" method="post" name="bbForm" onsubmit="return check();">
		<table>
			<tr>
				<th>숫자야구</th>
			</tr>
			<tr>
				<td id="resultTd">
				S : ${s }<br>
				B : ${b }<br>
				${result }
				</td>
			</tr>
			<tr>
				<td align="center"><input placeholder="${param.no }" name="no" maxlength="3"
					autocomplete="off" autofocus="autofocus"></td>
			</tr>
			<tr>
				<td align="center">
					<button>확인</button>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>