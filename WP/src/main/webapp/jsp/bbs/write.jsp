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
					placeholder="제목"></td>
			</tr>
			<tr>
				<td align="center"><textarea maxlength="440" name="txt"
						placeholder="내용"></textarea></td>
			</tr>
			<tr>
				<td align="center">
					<button>쓰기</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>