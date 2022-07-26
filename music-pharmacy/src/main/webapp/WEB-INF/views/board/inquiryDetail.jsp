<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상소문상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
<div class="content-main">
	<div class="page">
		<h2>${board.inq_title}</h2>
		<hr>
		<c:if test="${!empty board.inq_img}">
			<div class="align-center">
				<img src="${pageContext.request.contextPath}/upload/${board.inq_img}">
			</div>
		</c:if>
		<h2>Q.</h2>
		<p>${board.inq_question}</p>
		<h2>A.</h2>
		<p>${board.inq_answer}</p>
		
		<div class="align-center">
		<c:if test="${!empty pre_board.inq_num}">
		<div style="border-top:1px solid #eee;border-bottom:1px solid #eee;">
			<div>
				<span class="floating-left">이전글</span>
				<span><a href="inqDetail.do?inq_num=${pre_board.inq_num}">${pre_board.inq_title}</a></span>
				<span class="floating-right">${pre_board.inq_writer}</span>
			</div>
		</div>
		</c:if>
		<c:if test="${!empty next_board.inq_num}">
		<div style="border-top:1px solid #eee;border-bottom:1px solid #eee;">
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