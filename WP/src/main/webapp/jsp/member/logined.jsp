<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table id="loginedTable">
		<tr>
			<td rowspan="2" align="center" class="imgTd"><img
				src="img/${sessionScope.loginMember.photo }"></td>
			<td align="center">
				<%
				// ${sessionScope.��Ʈ����Ʈ��.��������� }
				%> 
				${sessionScope.loginMember.name }��<br>�������
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="MemberInfoC"><span class="material-symbols-outlined"> person </span></a> 
				<a href="LoginC"><span class="material-symbols-outlined"> logout </span></a>
			</td>
		</tr>
	</table>
</body>
</html>