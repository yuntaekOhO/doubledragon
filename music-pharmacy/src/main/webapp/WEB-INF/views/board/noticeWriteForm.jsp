<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
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
	<p>공지사항 게시판</p><br>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;글쓰기</p>
	<form id="write_form" action="noticeWrite.do" method="post" enctype="multipart/form-data">
	<ul>
		<li>
			<input type="text" name="not_title" id="not_title" maxlength="100"  placeholder="제목을 입력하세요.">
		</li>
		<li><br>
			<textarea rows="20" cols="100" name="not_content" id="not_content" placeholder="내용을 입력하세요."></textarea>
		</li>
		<li><br>
			<input type="file" name="not_img" id="not_img" accept="image/gif,image/png,image/jpeg">
		</li>
	</ul>
	<div class="align-right">
			<input type="submit" class="correction_btn" value="등록">
			<input type="button" class="correction_btn" value="취소" onclick="location.href='noticeList.do'">
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