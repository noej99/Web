<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<table id="bbsDetailTable">
	<form action="BBSDetailController" method="post" name="bbsUpdateForm" onsubmit="return bbsUpdateCheck();">
			<tr>
				<td align="center">
					<!-- <input name="no" class="no" value="${bm.no }" type="hidden"> -->
					<input class="no" value="${bm.no }" readonly="readonly">
				</td>
				<td>
					<input name="title" class="title" value="${bm.title }">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
						<textarea>${bm.txt }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<fmt:formatDate value="${bm.date }" type="both" dateStyle="long" timeStyle="long"/>
					<br>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button>수정</button>
	</form>
					<button onclick="deleteBBSMsg(${bm.no });">삭제</button>
				</td>
			</tr>
		</table>
	<br>
	<br>
	<br>
	<br>
</body>
</html>