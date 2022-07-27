<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/noticeWrite.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>
<div class="page">
	<a href="${pageContext.request.contextPath}/board/noticeList.do">어명이오</a>
	<p>공지사항 글수정</p><br>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;글쓰기</p>
	<form id="write_form" action="noticeUpdate.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="not_num" value="${board.not_num}">
	<ul>
		<li>
			<input type="text" name="not_title" id="not_title" maxlength="70">
		</li>
		<li><br>
			<textarea rows="20" cols="100" name="not_content" id="not_content">${board.not_content}</textarea>
		</li>
		<li><br>
			<input type="file" name="not_img" id="not_img" accept="image/gif,image/png,image/jpeg">
		</li>
	</ul>
	<div class="align-right">
			<input type="submit" value="등록">
			<input type="button" value="취소" onclick="location.href='noticeList.do'">
	</div>	
	</form>
	<div>
	
	</div>
</div>
<div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>