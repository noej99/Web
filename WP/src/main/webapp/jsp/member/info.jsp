<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<table id="registTable">
	<form action="MemberInfoC" method="post" enctype="multipart/form-data" name="memberUpdateForm" onsubmit="return memberUpdateCheck();">
			<tr>
				<th>회원정보</th>
			</tr>
			<tr>
				<td align="center">
					<input name="id" placeholder="${sessionScope.loginMember.id }" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="pw" value="${sessionScope.loginMember.pw }" type="password" maxlength="30" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="pwCheck" value="${sessionScope.loginMember.pw }" type="password" maxlength="30" autocomplete="off">
					
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="name" value="${sessionScope.loginMember.name }" maxlength="10" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td align="center">
				생년월일 : 
				<span id="memberInfoBD"><fmt:formatDate value="${sessionScope.loginMember.birth }" type="date" dateStyle="long"/></span>
				</td>
			</tr>
			<tr>
				<td align="center">
					<input name="addr1" value="${addr1}" placeholder="우편번호" autocomplete="off"><p>
					<input name="addr2" value="${addr2}" placeholder="주소" autocomplete="off"><p>
					<input name="addr3" value="${addr3}" placeholder="상세주소" autocomplete="off" maxlength="30"><br>
				</td>
			</tr>
			<tr>
				<td align="center">
				프사<br>
					<img id="memberInfoImg" src="img/${sessionScope.loginMember.photo }">
					<input name="photo" type="file"><br>
				</td>
			</tr>
			<tr>
				<td align="center">
					<button>수정</button>	
	</form>
					<button onclick="bye();">탈퇴</button>	
				</td>
			</tr>
		</table>
</body>
</html>