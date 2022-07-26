<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>저잣거리</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>

</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>

<div class="page">
 	<a href="${pageContext.request.contextPath}/board/freeboard.do">저잣거리</a>
 	<p>자유게시판</p><br>
 	<p style="float:left;">총 ${count}건</p>
	<!-- 검색 -->
	<div class="pull-left">
		<div class="search-bar" style="float:right;">
			<form id="search_form" action="noticeList.do" method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1">제목</option>
						<option value="2">작성자</option>
						<option value="3">내용</option>
					</select>
				</li>
				<li>
				<input type="search" size="16" 
					  name="keyword" id="keyword"
					  2
					  value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>		
			</form>
			
		</div>
	</div>
	<!-- 검색 끝 -->
	<br><br><br>
 	<!-- 로그인되어있으면 글쓰기가보임, 나중에 관리자 로그인시에만으로 바꿔야함 -->
 		   <c:if test="${!empty user_num}">
			<input type="button" value="글쓰기" 
			   onclick="location.href='freeWriteForm.do'">
			</c:if>   
			<input type="button" value="홈으로"
			 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">      
 	<br><br><br>
 	<c:if test="${count == 0}">
		<div>
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
			</tr>
		<c:forEach var="free_board" items="${list}">
			<tr>
				<td>${free_board.free_num}</td>
				<td><a href="freeDetail.do?free_num=${free_board.free_num}">${free_board.free_title}</a></td>
				<td>${free_board.free_title}</td>
				<td>${free_board.free_writer}</td>
				<td>${free_board.free_date}</td>
				<td>${free_board.free_hits}</td>
			</tr>
			</c:forEach>
		</table>
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