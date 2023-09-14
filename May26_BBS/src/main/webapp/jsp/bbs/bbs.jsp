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
			<td align="right"><a href="BBSWriteController">�۾���</a></td>
		</tr>
		<tr>
			<td align="center">
				<table id="bbs">
					<tr>
						<th class="th1">��ȣ</th>
						<th class="th2">����</th>
						<th>��¥</th>
					</tr>
					<c:forEach var="m" items="${msgs }">
						<tr class="dataTr" onclick="goBBSDetail(${m.no });">
							<td align="center">${m.no }</td>
							<td>${m.title }</td>
							<td align="center">${m.date }</td>
							<!--<fmt:formatDate value="${m.date }" type="date" dateStyle="long"/></td>-->
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center"><c:forEach var="p" begin="1"
					end="${pageCount }">
					<a href="BBSPageController?page=${p }" class="bbsPageChanger">${p }</a>
				</c:forEach></td>
		</tr>
		<tr>
			<td align="center">�˻�</td>
		</tr>
	</table>
</body>
</html>