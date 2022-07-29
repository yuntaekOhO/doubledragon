<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/noticeList.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/noticsListTitleContentFont.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/footer.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/noticeList.js"></script>

</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/noticeHeader.jsp"/>
</div>

<div class="page">
 	<a href="${pageContext.request.contextPath}/board/noticeList.do">어명이오</a>
 	<p>공지사항 게시판</p><br>
 	<hr class="noticeList_hr">
 	<p style="float:left;">total - ${count} 건</p>
	<!-- 검색 -->
	<div class="pull-left">
		<div class="search-bar" style="float:right;">
			<form id="not_search_form" action="noticeList.do" method="get">
			<input type="search" class="input-search" id="keyword" name="keyword" value="${param.keyword}">
			<select id="keyfield" name="keyfield" style="float:right;height:35px;">
				<option value="1">제목</option>
				<option value="2">내용</option>
			</select>
			<input type="submit" class="input-search-submit" value="검색">
		</form>
		</div>
	</div>
	<!-- 검색 끝 -->
	
 	<div class="list-space align-right">
 	<!-- 관리자만 글쓰기 가능 , 글쓰기버튼ㅇ ㅐㅗㅇㄹ냐-->
 		<c:if test="${!empty user_num && user_auth==3}">
	 		<div>
				<form class="floating-right">
					<input type="button" value="글작성" onclick="location.href='noticeWriteForm.do'">
				</form>
			</div>
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
 	<!-- 공지사항에 글이 있을때 -->
	<c:forEach var="board" items="${list}">
		<div style="width:90%;margin:0 auto;">
		<div class="noticeList_title_content">
			<input type="hidden" id="not_num" value="${board.not_num}">
			
			<strong></strong>  <a href="detail.do?not_num=${board.not_num}" class="noticeList_title">${board.not_title}</a>
			<span class="floating-right">${board.not_writer}</span><br><br>
			<strong></strong>  <a href="detail.do?not_num=${board.not_num}" class="noticeList_content">${board.not_content}</a>
			<span class="floating-right">${board.not_date}</span><br>
			<span class="floating-right">${board.not_hits} views</span><br><br>
		</div>
			<%-- <div class="floating-right">
			<c:if test="${!empty user_num && user_auth==3}">
				<input class="floating-right" type="button" value="수정" onclick="location.href='noticeUpdateForm.do?not_num=${board.not_num}'"> 
				 <input class="floating-right" type="button" value="삭제" id="noticeDelete_btn">
			</c:if>
			</div> --%>
			<br>
			<hr width="100%" size="3">
		</div>
		</c:forEach>
		<div class="align-center">
			${page}
		</div>
 	</c:if>
</div>
<div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>







