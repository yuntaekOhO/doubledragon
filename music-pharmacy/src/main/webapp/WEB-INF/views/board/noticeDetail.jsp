<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.reply.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>${board.not_title}</h2>
		<ul class="detail-info">
			<li> <!-- 머야  -->
				<c:if test="${!empty board.mem_photo}">
				<img src="${pageContext.request.contextPath}/upload/${board.mem_photo}" width="40" height="40" class="my-photo">
				</c:if>
				<c:if test="${empty board.mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
				</c:if>
			</li>
			<li>
				${board.mem_id}<br>
				조회 : ${board.not_hits}
			</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty board.not_img}">
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/upload/${board.filename}" class="detail-img">
		</div>
		</c:if>
		<p>
			${board.not_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">

</div>
</body>
</html>





