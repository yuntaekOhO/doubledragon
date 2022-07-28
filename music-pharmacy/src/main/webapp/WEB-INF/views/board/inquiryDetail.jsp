<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상소문상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/inquirystyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inquiryBoard.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
<div>
	<div class="page">
		<h2>${board.inq_title}</h2>
		<c:if test="${empty board.inq_modify_date}">
		<span class="floating-right">작성일 ${board.inq_date}</span><br>
		</c:if>
		<c:if test="${!empty board.inq_modify_date}">
		<span class="floating-right">수정일 ${board.inq_modify_date}</span><br>
		</c:if>
		<hr>
		<c:if test="${!empty user_num && user_auth==3}">
		<div class="floating-right">
			<input type="button" value="수정" onclick="location.href='inquiryUpdateForm.do?inq_num=${board.inq_num}'">
			<input id="inquiryDelete_btn" type="button" value="삭제">
		</div>
		</c:if>
		<input type="hidden" id="inq_num" value="${board.inq_num}">
		<h2>Q.</h2>
		<div style="width:90%;margin:0 auto;">
			<p>${board.inq_question}</p>
		</div>
		<h2>A.</h2>
		<div style="width:90%;margin:0 auto;">
		<p>${board.inq_answer}</p>
		<c:if test="${!empty board.inq_img}">
			<div class="align-center">
				<!-- <form method="post" enctype="multipart/form-data">
					<input type="file" accept="image/gif,image/png,image/jpeg" style="display:none;"> -->
					<img src="${pageContext.request.contextPath}/upload/${board.inq_img}">
				<!-- </form> -->
			</div>
		</c:if>
		</div>
		<br><br>
		<div class="align-center">
		<c:if test="${!empty pre_board.inq_num}">
		<div style="border-top:1px solid #999;border-bottom:1px solid #999;">
			<div>
				<span class="floating-left">이전글</span>
				<span><a href="inqDetail.do?inq_num=${pre_board.inq_num}">${pre_board.inq_title}</a></span>
				<span class="floating-right">${pre_board.inq_writer}</span>
			</div>
		</div>
		</c:if>
		<c:if test="${!empty next_board.inq_num}">
		<div style="border-top:1px solid #999;border-bottom:1px solid #999;">
			<div>
				<span class="floating-left">다음글</span>
				<span><a href="inqDetail.do?inq_num=${next_board.inq_num}">${next_board.inq_title}</a></span>
				<span class="floating-right">${next_board.inq_writer}</span>
			</div>
		</div>
		</c:if>
		</div>
		
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>