<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���ھ߱�</title>
<link rel="stylesheet" href="css/bb.css">
<script type="js/kwonValidChecker.js"></script>
<script type="js/bbChecker.js"></script>
</head>
<body>
	<form action="Controller" method="post" name="bbForm" onsubmit="return check();">
		<table>
			<tr>
				<th>���ھ߱�</th>
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
					<button>Ȯ��</button>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>