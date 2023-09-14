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
	<table id="drDetailTable">
	<form action="DRDetailCon0troller" method="post" enctype="mutlipart/form-data" name="drUpdateForm" onsubmit="">
		<tr>
			<td>
				<input name="no" class="no" value="${f.no }" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td>
				<input name="title" class="title" value="${f.title }">
			</td>
		</tr>
		<tr>
			<td align="center">
				파일
			</td>
			<td  colspan="2" align="center">
				<a href="drFile/${f.file }">다운로드</a><br>
				바꾸기 : <input name="file" type="file">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<fmt:formatDate value="${bm.date }" type="both" dateStyle="long" timeStyle="short"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button>수정</button>
			</td>
		</tr>
		<tr>
			<td>
			
			</td>
		</tr>
	</form>
		<tr>
			<td>
			
			</td>
		</tr>
		
	</table>
</body>
</html>