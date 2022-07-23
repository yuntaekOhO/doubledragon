<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>저잣거리</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
	<div>
	<div class="page">
 	<a href="${pageContext.request.contextPath}/board/freeBoard.do">저잣거리</a>
 	<p>공지사항 게시판</p><br>
 	<p style="float:left;">총 27건</p>
	<!-- 검색 -->
	<div class="pull-left">
		<div class="search-bar" style="float:right;">
			<form>
				<input type="search" class="input-search"> <input
					type="submit" class="input-search-submit" value="검색">
			</form>
		</div>
	</div>
	<!-- 검색 끝 -->
	
	<div class="floating-clear">
		<h3>자주 묻는 질문</h3>
		<%-- 관리자 전용 글작성 버튼 --%>
		<c:if test="${!empty user_num && user_auth==3}">
		<div>
			<button>글 작성</button>
		</div>
		</c:if>
		<div style="padding:0 70px">
			<strong>Q</strong> <span>질문 내용</span><br><br>
			<strong>A</strong> <span>답변 내용</span><br><br>
		</div>
		<hr width="90%">
		
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	</div>
	
	<div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>

</div>
	</div>
</body>
</html>