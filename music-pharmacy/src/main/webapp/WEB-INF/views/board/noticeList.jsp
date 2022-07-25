<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">

</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>

<div class="page">
 	<a href="${pageContext.request.contextPath}/board/noticeList.do">어명이오</a>
 	<p>공지사항 게시판</p><br>
 	<p style="float:left;">총 27건</p>
	<!-- 검색 -->
	<div class="pull-left">
		<div class="search-bar" style="float:right;">
			<form id="search_form" action="noticeList.do" method="get">
				<input type="search" class="input-search"> <input
					type="submit" class="input-search-submit" value="검색">
			</form>
		</div>
	</div>
	<!-- 검색 끝 -->
 	<div class="list-space align-right">
 	<!-- 로그인되어있으면 글쓰기가보임, 나중에 관리자 로그인시에만으로 바꿔야함 -->
 		<c:if test="${empty user_num}"> <!--로그인 안됨-->
 		<input type="button" value="글쓰기" onclick="location.href='noticeWriteForm.do'">
 		</c:if>
 	</div><br><br><br>
 	<c:if test="${count == 0}">
 	<!-- 걍넣어봄 -->
 	<div class="container">
  <div class="main">
    <div class="quote-container">
  <i class="pin"></i>
  <div class="note yellow">
    <span class="small"></span>
    <h2>게시된 어명 없음</h2>
  </div>
</div>

<div class="quote-container">
  <i class="pin"></i>
  <div class="note blue">
    <span class="small"></span>
    <h2>게시된 어명 없음</h2>
    <img src="http://placehold.it/150x150" class="imgleft"><p> <a href="#">Read More</a></p>
  </div>
</div>

  </div>

</div>
 	</c:if>
 	<c:if test="${count > 0}">
 	<!-- 공지사항있을때 html 넣어야함 -->
 	
 	</c:if>

</div>
<div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>