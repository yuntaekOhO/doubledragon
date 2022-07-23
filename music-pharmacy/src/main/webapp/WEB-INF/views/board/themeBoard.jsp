<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동의보감</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>

<div class="page">
 	<a href="${pageContext.request.contextPath}/board/themeBoard.do">동의보감</a>
 	<p style="float:left;">총 27건</p>
 	
	<!-- 검색 -->
	<div class="pull-left">
	</div>
	</div>
	<!-- 검색 끝 -->
	
 	<div class="list-space align-right">
 	<!-- 로그인되어있으면 글쓰기가보임, 나중에 관리자 로그인시에만으로 바꿔야함 -->
 		<c:if test="${empty user_num}"> <!--로그인 안됨-->
 		<input type="button" value="글쓰기" onclick="location.href='freeWriteForm.do'">
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
  </div>
</div>

<div class="quote-container">
  <i class="pin"></i>
  <div class="note blue">
    <span class="small"></span>

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

</body>
</html>