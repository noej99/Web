<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="BBSWC" method="post" name="bbsWriteForm"
		onsubmit="return bbsWriteCheck();">
		<input name="token" type="hidden" value="${token }">
		<table id="bbsWriteArea">
			<tr>
				<td align="center"><input maxlength="70" name="title"
					placeholder="����"></td>
			</tr>
			<tr>
				<td align="center"><textarea maxlength="440" name="txt"
						placeholder="����"></textarea></td>
			</tr>
			<tr>
				<td align="center">
					<button>����</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>