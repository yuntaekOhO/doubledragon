<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<td class="free_theme" rowspan="" width="15%" style="border-bottom:1px solid #423207;">
					<c:if test = "${board.free_num>0}">
					저잣거리
					</c:if>
					<c:if test = "${board.the_num>0}">
					동의보감
					</c:if>
					<c:if test = "${board.not_num>0}">
					어명이오
					</c:if>
					<c:if test = "${board.inq_num>0}">
					상소문
					</c:if>
				</td>
				<td class="free_theme" rowspan="" width="15%" style="border-bottom:1px solid #423207;">
					<c:if test = "${board.free_num>0}">
					<c:if test="${flist[status.index].free_code==1}">
					자유
					</c:if>
					<c:if test="${flist[status.index].free_code==2}">
					추천
					</c:if>
					</c:if>
					
					<c:if test = "${board.the_num>0}">
					<c:if test="${tlist[status.index].the_code==1}">
					희
					</c:if>
					<c:if test="${tlist[status.index].the_code==2}">
					노
					</c:if>
					</c:if>
				</td>
				<c:if test = "${board.mus_num>0}">
				<td>
					<c:if test="${mlist[status.index].mus_genre eq '발라드'}">
					발라드
					</c:if>
					<c:if test="${mlist[status.index].mus_genre eq 'pop'}">
					pop
					</c:if>
					<c:if test="${mlist[status.index].mus_genre eq '댄스'}">
					댄스
					</c:if>
					<c:if test="${mlist[status.index].mus_genre eq '힙합'}">
					힙합
					</c:if>
					<c:if test="${mlist[status.index].mus_genre eq '인디'}">
					인디
					</c:if>
					<c:if test="${mlist[status.index].mus_genre eq 'etc'}">
					etc
					</c:if>
				</td>
				</c:if>
				<c:if test = "${board.mus_num>0}">
				<td>
					
				</td>
				</c:if>
			</tr>
			</c:forEach>

		</table>
		<hr size="1" noshade="noshade" width="100%">
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