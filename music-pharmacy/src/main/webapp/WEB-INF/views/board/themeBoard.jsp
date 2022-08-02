<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동의보감</title>
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
 	<a href="${pageContext.request.contextPath}/board/themeBoard.do">동의보감</a>
 	<p>음악추천게시판</p>
 	<p style="float:left;">총 ${count}건</p>
 	<div>
 	<!-- 검색 -->
	<div class="search-bar">
		<form id="the_search_form" action="themeBoard${code}.do" method="get">
		   
			<input type="search" class="input-search" id="keyword" name="keyword" value="${param.keyword}">
			<select id="keyfield" name="keyfield" style="float:right;height:35px;">
				<option value="1">제목</option>
				<option value="2">닉네임</option>
				<option value="3">글 내용</option>
			</select>
			<input type="submit" class="input-search-submit" value="검색">
		</form>
	</div>
	<!-- 검색 끝 -->
	   <div class="align-right">
 		   <c:if test="${!empty user_num}">
			<input type="button" class="correction_btn" value="글쓰기"
			   onclick="location.href='themeWriteForm.do'">
			 </c:if>  
		</div>
			   
 	<c:if test="${count == 0}">
		<div>
			표시할 게시물이 없습니다.
		</div>
	</c:if>
	<c:if test="${count > 0}">
	
	
	<hr size="1" noshade="noshade" width="100%">
	<table class="free_table" cellpadding="10" width="100%">

		<c:forEach var="board" items="${list}">
		<tr>
		<td class="free_theme" rowspan="2" width="15%" style="border-bottom:1px solid #423207;">
					<c:if test = "${board.the_code==1}">
					희
					</c:if>
					<c:if test = "${board.the_code==2}">
					노
					</c:if>
					<c:if test = "${board.the_code==3}">
					애
					</c:if>
					<c:if test = "${board.the_code==4}">
					락
					</c:if>
		</td>
			<td align="left" width="70%"><a href="themeDetail.do?the_num=${board.the_num}">${board.the_title}</a></td>
			<td align="right" width="15%">view : ${board.the_hits}</td>
		</tr>
		<tr>
			<td align="left" width="70%" style="border-bottom:1px solid #423207;">${board.mus_title}, ${board.mus_singer}</td>
			<td align="right" width="15%" style="border-bottom:1px solid #423207;">${board.nick} 님 작성</td>
			
		</tr>
		</c:forEach>
	</table>
	<hr size="1" noshade="noshade" width="100%">
	
	<div class="align-center">
			${page}
	</div>
	</c:if>


</div>


</div>
<div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>

</body>
</html>