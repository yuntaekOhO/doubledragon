<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/noticeList.css" type="text/css"> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/noticsListTitleContentFont.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/footer.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/noticeList.js"></script>

</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>

<div class="page">
 	<a href="${pageContext.request.contextPath}/board/noticeList.do">어명이오</a>
 	<p>공지사항 게시판</p>
 	<!-- <hr class="noticeList_hr"> -->
 	<p style="float:left;">총 ${count}건</p>
	<!-- 검색 -->
	<!-- <div class="pull-left"> -->
		<div class="search-bar">
			<form id="not_search_form" action="noticeList.do" method="get">
			<input type="search" class="input-search" id="keyword" name="keyword" value="${param.keyword}">
			<select id="keyfield" name="keyfield" style="float:right;height:35px;">
				<option value="1">제목</option>
				<option value="2">내용</option>
			</select>
			<input type="submit" class="input-search-submit" value="검색">
		</form>
		</div>
	<!-- </div> -->
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
 	</div>
 	
 	<c:if test="${count == 0}">
   <div>
			표시할 게시물이 없습니다.
		</div>
		</c:if>

 	<c:if test="${count > 0}">
 	<!-- 공지사항에 글이 있을때 -->
 	<hr size="1" noshade="noshade" width="100%">
	<table class="notice_table" cellpadding="10" width="100%">
	<c:forEach var="board" items="${list}">
			<tr>
			<td rowspan="2" width="15%" style="border-bottom:1px solid #423207;">${board.not_num}</td>
			<%-- <input type="hidden" id="not_num" value="${board.not_num}"> --%>
		    <td align="left" width="70%"><a href="detail.do?not_num=${board.not_num}" class="noticeList_title">${board.not_title}</a>
			<td align="right" width="15%">view : ${board.not_hits}</td>
			</tr>
			
			<tr>
			<td align="left" width="70%" style="border-bottom:1px solid #423207;">${board.not_content}</td>
			<td align="right" width="15%" style="border-bottom:1px solid #423207;">${board.not_writer}님 작성</td>
			</tr>
		
		</c:forEach>
		</table>
		<hr size="1" noshade="noshade" width="100%">
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







