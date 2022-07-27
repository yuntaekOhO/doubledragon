<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상소문작성</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/inquirystyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inquiryBoard.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
	<div>
	<div class="page">
	<a href="${pageContext.request.contextPath}/board/inquiryBoard.do">상소문</a>
	<p>상소문 작성</p>
	<hr>
	<div style="width:90%;margin:0 auto">
	<form id="inq_write_form" action="writeInq.do" method="post" enctype="multipart/form-data">

			<!-- <li>
				<label for="inq_title">제목</label>
				<input type="text" name="inq_title" id="inq_title">
			</li> -->
			<div>
				<textarea name="inq_question" id="inq_question" rows="7" cols="200" placeholder="질문을 입력하세요."
				style="resize:none;"></textarea><br>
			</div>
			<div>
				<textarea name="inq_answer" id="inq_answer" rows="15" cols="200" placeholder="답변을 입력하세요."
				style="resize:none;"></textarea><br>
			</div>
			<div>
				<input type="file" name="inq_img" id="inq_img" accept="image/gif,image/png,image/jpeg" value="첨부파일"><br>
			</div>

		<div class="align-center" style="margin-top:50px;">
			<input type="submit" value="글쓰기">
			<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/board/inquiryBoard.do'">
		</div>
		
	</form>
	</div>
	</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>