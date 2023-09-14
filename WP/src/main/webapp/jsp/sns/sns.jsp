<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!-- 로그인 안하면 안보이게 -->
	<c:if test="${sessionScope.loginMember != null }">
		<form action="SNSWriteC" name="snsWriterForm"
			onsubmit="return snsCheck();">
			<table id="snsWriteTable">
				<tr>
					<td align="center"><input name="token" type="hidden"
						value="${token }">
						<table>
							<tr>
								<td align="center"><textarea name="txt" maxlength="400"
										placeholder="입력"></textarea></td>
								<td align="center">
									<button>쓰기</button>
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</form>
	</c:if>
	<form action="SNSSearchC">
		<table id="snsSearchArea">
			<tr>
				<td align="center">
					<input name="search" maxlength="40" autocomplete="off">
				</td>
				<td>
					<button>검색</button>
				</td>
			</tr>
		</table>
	</form>
	<c:forEach var="sm" items="${msgs }">
		<table class="aMsg">
			<tr>
				<td align="left" rowspan="3" class="td1"><img
					src="img/${sm.photo }"></td>
				<td align="right" class="td2">${sm.writer }</td>
			</tr>
			<tr>
				<td align="right" class="td4">${sm.txt }</td>
			</tr>
			<tr>
				<td class="td5">
				<c:forEach var="sr" items="${sm.replys }">
					<span class="snsReplyWriter">${sr.writer } : </span>
					${sr.txt } -
					<fmt:formatDate value="${sr.date }" type="both" dateStyle="short" timeStyle="short"/>
					<c:choose>
						<c:when test="${sessionScope.loginMember.id == sr.writer }">
							<button onclick="updateSNSReply(${sr.no }, '${sr.txt }');">수정</button>
							<button onclick="deleteSNSReply(${sr.no });">삭제</button>
						</c:when>
					</c:choose>
					<br>
				</c:forEach>
					<c:if test="${sessionScope.loginMember != null }">
						<form action="SNSReplyWriteC" onsubmit="return snsReplyWriteCheck(this);">
							<input name="token" value="${token }" type="hidden">
							<input name="ws_no" value="${sm.no }" type="hidden">
							<span class="snsReplyWriter">${sessionScope.loginMember.id }</span>
							<input name="txt" maxlength="80" autocomplete="off">
							<button>쓰기</button>
						</form>
					</c:if>
				</td>
			</tr>
			<tr>
				<td align="right" class="td3">
				<fmt:formatDate value="${sm.date }" type="both" dateStyle="long" timeStyle="short"/>
				</td>
			</tr>
			<c:if test="${sm.writer == sessionScope.loginMember.id }">
			<tr>
				<td align="right" colspan="2">
					<button onclick="updateSNSMsg(${sm.no }, '${sm.txt }');">수정</button>
					<button onclick="deleteSNSMsg(${sm.no });">삭제</button>
				</td>
			</tr>
			</c:if>
		</table>
	</c:forEach>
	<!-- 
		&lt; <
		&gt; >
		&nbsp   띄어쓰기
	 -->
	 <c:if test="${page != 1 }">
	<a href="SNSPageC?page=${page - 1 }" class="snsL">&lt;</a>
	 </c:if>
	<c:if test="${page != pageCount }">
	<a href="SNSPageC?page=${page + 1 }" class="snsR">&gt;</a>
	</c:if>
</body>
</html>