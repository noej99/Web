<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="RegisterC" method="post" enctype="multipart/form-data" name="registForm" onsubmit="return registerCheck();">
		<table id="registTable">
			<tr>
				<th>ȸ������</th>
			</tr>
			<tr>
				<td align="center">
					<input name="id" placeholder="id" maxlength="10" autofocus="autofocus" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="pw" placeholder="pw" type="password" maxlength="30">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="pwCheck" placeholder="pwȮ��" type="password" maxlength="30">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="name" placeholder="�̸�" maxlength="10" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td align="center">
				�������<br>
					<select name="y">
						<c:forEach var="y" begin="${cy - 100 }" end="${cy }">
							<option>${y }</option>
						</c:forEach>
					</select>��
					<select name="m">
						<c:forEach var="m" begin="1" end="12">
							<option>${m }</option>
						</c:forEach>
					</select>��
					<select name="d">
						<c:forEach var="d" begin="1" end="31">
							<option>${d }</option>
						</c:forEach>
					</select>��
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="addr1" placeholder="�����ȣ" autocomplete="off"><p>
					<input name="addr2" placeholder="�ּ�" autocomplete="off"><p>
					<input name="addr3" placeholder="���ּ�" autocomplete="off" maxlength="30"><br>
				</td>
			</tr>
			<tr>
				<td align="center">
				����<br>
					<input name="photo" type="file"><br>
				</td>
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