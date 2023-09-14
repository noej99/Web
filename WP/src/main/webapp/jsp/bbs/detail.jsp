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
	<c:forEach var="bd" items="${bmsgs }">
		<table id="bbsDetailTitle">
			<tr>
				<td class="title">${bd.title }</td>
			</tr>
		</table>
		<table id="bbsDetailWriter">
			<tr>
				<td class="writer">�ۼ��� : ${bd.writer }</td>
			</tr>
			<tr>
				<td class="date"><fmt:formatDate value="${bd.date }"
						type="both" dateStyle="long" timeStyle="short" /></td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${sessionScope.loginMember.id == bd.writer }">
						<td align="right">
							<button
								onclick="updateBBSMsg(${bd.no }, '${bd.title }', '${bd.txt }');">����</button>
							<button onclick="deleteBBSMsg(${bd.no });">����</button>
						</td>
					</c:when>
				</c:choose>
			</tr>
		</table>
		<table id="bbsDetailTxt">
			<tr>
				<td class="txt">${bd.txt }</td>
			</tr>
		</table>
		<table id="bbsDetailReply">
			<tr>
				<td>���</td>
				<td align="right"><c:if
						test="${sessionScope.loginMember != null }">
						<button onclick="writeReply(${bd.no }, '${bd.writer }');">����</button>
					</c:if></td>
			</tr>
			<tr>
				<td><c:forEach var="br" items="${bd.replys }">
						<span class="bbsReplyWriter">${br.writer }</span>
				${br.txt } - 
				<fmt:formatDate value="${br.date }" type="both" dateStyle="short"
							timeStyle="short" />
					</c:forEach></td>
			</tr>
		</table>
	</c:forEach>
</body>
</html>