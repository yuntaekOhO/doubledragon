<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통합검색</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
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
 	<a href="${pageContext.request.contextPath}/board/freeBoard.do">통합검색</a>
 	<p>검색 결과</p>
 	<p style="float:left;">총 ${count}건</p>
<!-- 검색 -->
	<div class="search-bar">
		<form id="inq_search_form" action="mainSearch.do" method="get">
			<input type="search" class="input-search" id="keyword" name="keyword" value="${param.keyword}">
			<input type="submit" class="input-search-submit" value="검색">
		</form>
	</div>
	<!-- 검색 끝 -->
    
 	    <div class="align-right">

		</div>

 	<c:if test="${count == 0}">
		<div>
			검색된 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		
		<!-- 게시글 시작 -->
		<hr size="1" noshade="noshade" width="100%">
		<table class="free_table" cellpadding="10" width="100%">
			<c:forEach var="board" items="${list}" varStatus="status">
			<tr>
				<!-- 게시판 -->
				<td class="free_theme" rowspan="" width="15%" style="border-bottom:1px solid #423207;">
					<c:if test = "${!empty board.free}">
					저잣거리<input type="hidden" name="free_num" value="${board.free.free_num}">
					</c:if>
					<c:if test = "${!empty board.the}">
					동의보감<input type="hidden" name="the_num" value="${board.the.the_num}">
					</c:if>
					<c:if test = "${!empty board.notice}">
					어명이오<input type="hidden" name="not_num" value="${board.notice.not_num}">
					</c:if>
					<c:if test = "${!empty board.inq}">
					상소문<input type="hidden" name="inq_num" value="${board.inq.inq_num}">
					</c:if>
				</td>
				<!-- 분류 -->
				<td class="free_theme" rowspan="" width="10%" style="border-bottom:1px solid #423207;">
				<c:choose>
				<c:when test="${!empty board.free}">
					<c:if test="${board.free.free_code == 1}">
					자유
					</c:if>
					<c:if test="${board.free.free_code == 2}">
					추천
					</c:if>
				</c:when>
				<c:when test="${!empty board.the || !empty board.mus}">
					<c:if test="${board.the.the_code == 1}">
					희
					</c:if>
					<c:if test="${board.the.the_code == 2}">
					노
					</c:if>
					<c:if test="${board.the.the_code == 3}">
					애
					</c:if>
					<c:if test="${board.the.the_code == 4}">
					락
					</c:if>
				</c:when>
				</c:choose>
				</td>
				<!-- 제목 -->
				<td class="free_theme" rowspan="" width="25%" style="border-bottom:1px solid #423207;">
				<c:choose>
				<c:when test="${!empty board.free}">
				<a href="${pageContext.request.contextPath}/board/freeDetail.do?free_num=${board.free.free_num}">${board.free.free_title}</a>
				</c:when>
				<c:when test="${!empty board.the}">
				<a href="${pageContext.request.contextPath}/board/themeDetail.do?the_num=${board.the.the_num}">${board.the.the_title}</a>
				</c:when>
				<c:when test="${!empty board.inq}">
				<a href="${pageContext.request.contextPath}/board/inqDetail.do?inq_num=${board.inq.inq_num}">${board.inq.inq_question}</a>
				</c:when>
				<c:when test="${!empty board.notice}">
				<a href="${pageContext.request.contextPath}/board/detail.do?not_num=${board.notice.not_num}">${board.notice.not_title}</a>
				</c:when>
				</c:choose>
				</td>
				<!-- 닉네임 -->
				<td class="free_theme" rowspan="" width="15%" style="border-bottom:1px solid #423207;">
				<c:choose>
				<c:when test="${!empty board.free}">
				${board.free.nick}
				</c:when>
				<c:when test="${!empty board.the || !empty board.mus}">
				${board.the.nick}
				</c:when>
				<c:when test="${!empty board.inq}">
				${board.inq.nick}
				</c:when>
				<c:when test="${!empty board.notice}">
				${board.notice.id}
				</c:when>
				</c:choose>
				</td>
				<!-- 작성일 -->
				<td class="free_theme" rowspan="" width="15%" style="border-bottom:1px solid #423207;">
				<c:choose>
				<c:when test="${!empty board.free}">
				<c:if test="${empty board.free.free_modify_date}">
				<p>작성일</p>${board.free.free_date}
				</c:if>
				<c:if test="${!empty board.free.free_modify_date}">
				<p>수정일</p>${board.free.free_modify_date}
				</c:if>
				</c:when>
				<c:when test="${!empty board.the || !empty board.mus}">
				<c:if test="${empty board.the.the_modify_date}">
				<p>작성일</p>${board.the.the_date}
				</c:if>
				<c:if test="${!empty board.the.the_modify_date}">
				<p>수정일</p>${board.the.the_modify_date}
				</c:if>
				</c:when>
				<c:when test="${!empty board.inq}">
				<c:if test="${empty board.inq.inq_modify_date}">
				<p>작성일</p>${board.inq.inq_date}
				</c:if>
				<c:if test="${!empty board.inq.inq_modify_date}">
				<p>수정일</p>${board.inq.inq_modify_date}
				</c:if>
				</c:when>
				<c:when test="${!empty board.notice}">
				<c:if test="${empty board.notice.not_modify_date}">
				<p>작성일</p>${board.notice.not_date}
				</c:if>
				<c:if test="${!empty board.notice.not_modify_date}">
				<p>수정일</p>${board.notice.not_modify_date}
				</c:if>
				</c:when>
				</c:choose>
				</td>
			</tr>
			</c:forEach>

		</table>
		<!-- 게시글 끝 -->
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