<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상소문작성</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inquiryBoard.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
<div class="content-main">
	<h2>상소문 글작성</h2>
	<form id="inq_write_form" action="writeInq.do" method="post" enctype="multipart/form-data">
		<ul>
			<li>
				<label for="inq_title">제목</label>
				<input type="text" name="inq_title" id="inq_title">
			</li>
			<li>
				<label for="inq_question">질문</label>
				<textarea name="inq_question" id="inq_question" rows="2" cols="50"></textarea>
			</li>
			<li>
				<label for="inq_img">파일</label>
				<input type="file" name="inq_img" id="inq_img" accept="image/gif,image/png,image/jpeg">
			</li>
			<li>
				<label for="inq_answer">답변</label>
				<textarea name="inq_answer" id="inq_answer" rows="5" cols="50"></textarea>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="글쓰기">
			<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/board/inquiryBoard.do'">
		</div>
	</form>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>