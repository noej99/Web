<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>숫자야구</title>
</head>
<body>
	<form action="BBController" method="post" name="bbForm" onsubmit="return check();">
		<table id="bbTable">
			<tr>
				<th id="bbTitle">숫자야구</th>
			</tr>
			<tr>
				<td id="resultTd">
				S : ${s }<br>
				B : ${b }<br>
				${result }
				</td>
			</tr>
			<tr>
				<td align="center"><input id="bbInput" placeholder="${param.no }" name="no" maxlength="3"
					autocomplete="off" autofocus="autofocus"></td>
			</tr>
			<tr>
				<td align="center">
					<button id="bbBtn">확인</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>