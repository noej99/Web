0<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
	<form action="DRUploadController" method="post"
		enctype="multipart/form-data" name="drUploadForm"
		onsubmit="return drUploadCheck();">
		<table id="bbsWriteTable">
			<tr>
				<td align="center"><br> <input maxlength="80" name="title"
					placeholder="제목" autocomplete="off" autofocus="autofocus">
				</td>
			</tr>
			<c:forEach var="f" items="${files }">
				<tr class="dataTr" onclick="goBBSDetail(${f.no })">
					<td align="center">%{f.no }</td>
					<td>%{f.tilte }</td>
					<td align="center"><fmt:formatDate value="${f.date }"
							type="long" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td align="center">
					<button>업로드</button>
					<br>
				<br>
				</td>
			</tr>
			<tr>
				<td align="center"><c:forEach var="p" begin="1"
						end="${pageCount }">
						<a href="DRPageController?page=${p }" class="bbsPageController">${p }</a>
					</c:forEach></td>
			</tr>
			<tr>
				<td align="center">검색</td>
			</tr>
		</table>
	</form>
</body>
</html>