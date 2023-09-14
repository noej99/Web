<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table id="bbsArea" border="1">
		<tr>
		<c:if test="${sessionScope.loginMember != null }">
			<td align="right"><a href="BBSWC">�۾���</a></td>
		</c:if>
		<tr>
			<td align="center">
				<table id="bbs">
					<tr>
						<th class="th1">��ȣ</th>
						<th class="th2">����</th>
						<th>�ۼ���</th>
						<th align="right">��¥</th>
					</tr>
					<c:forEach var="m" items="${msgs }">
						<tr class="dataTr" onclick="goBBSDetail(${m.no });">
							<td align="center">${m.no }</td>
							<td align="center">${m.title }</td>
							<td align="center">${m.writer }</td>
							<td align="right">${m.date }</td>
							<!--<fmt:formatDate value="${m.date }" type="date" dateStyle="long"/></td>-->
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center"><c:forEach var="p" begin="1"
					end="${pageCount }">
					<a href="BBSPC?page=${p }" class="bbsPageChanger">${p }</a>
				</c:forEach></td>
		</tr>
	<form action="BBSSearchC">
			<tr>
				<td align="center" class="bbsSearch">
					<input name="search" maxlength="40" autocomplete="off">
					<button>�˻�</button>
				</td>
			</tr>
	</form>
	</table>
</body>
</html>