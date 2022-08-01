<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header1.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/noticeList.js"></script>
</head>
<body>
   
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div id="content">
	
		<div class="best-boyak">
				<span>BEST보약</span>
		</div>
		
		<div style="float:left;width:50%;">
		
		<div class="month-rank">
		    <c:forEach var="theme" items="${theme}">
			<div class="rank-detail">
				<a href="${pageContext.request.contextPath}/board/themeDetail.do?the_num=${theme.the_num}">
					<img src="/music-pharmacy/upload/${theme.mus_img}" width="140" height="140" vspace="10" hspace="20"/></a>
					<br>
					<b>[${theme.mus_title}]</b><br>
					<b>${theme.mus_singer}</b><br>
					${theme.nick}님<!-- 순위 -->
				</div>
			</c:forEach>
		</div>
	
		
	</div>
	
	<div style="float:right;width:50%;">
	<div class="mini-notice">
		<h5>어명이오</h5>
		<div></div>
		<ul>
		<c:forEach var="notice" items="${notice}">
		<li><a href="${pageContext.request.contextPath}/board/detail.do?not_num=${notice.not_num}">${notice.not_title}</a></li>
		</c:forEach>
		</ul>
	</div>
	<div class="mini-content-square1">
		<h5>고객센터</h5>
		<div></div>
		<h5>kakao ID |<br>musicpharamacy
		<br>
		Mail | musicpharamacy@naver.com
		</h5>
	</div>
	
	<div class="mini-content-square2">
		<h5>고객센터</h5>
		<div></div>
		<h5>kakao ID |<br>musicpharamacy
		<br>
		Mail | musicpharamacy@naver.com
		</h5>
	</div>
	</div>
	
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



