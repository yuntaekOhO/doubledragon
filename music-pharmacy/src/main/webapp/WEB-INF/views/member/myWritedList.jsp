<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가쓴글</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/inquirystyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inquiryBoard.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
	<div>
	<div class="page">
 	<a href="${pageContext.request.contextPath}/member/myWritedList.do">내가 쓴 글</a>
 	<hr>
 	
 	<div style="width:90%;">
	<!-- 저잣거리 -->
	<div class="floating-left">
	<span style="float:left;">저잣거리 total - ${freeCnt} 건</span>	
		<c:if test="${freeCnt==0}">
		<div>
				표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${freeCnt > 0}">
		<c:forEach var="board" items="${flist}">
		<div style="width:40%;margin:0 auto;">
		<div>
			<input type="hidden" id="free_num" value="${board.free_num}">
			<a href="${pageContext.request.contextPath}/board/freeDetail.do?free_num=${board.free_num}">${board.free_title}</a>
			<span class="floating-right">${board.nick}</span><br><br>
			
		</div>
			<div class="floating-right">
			<c:if test="${!empty user_num && user_num==board.mem_num && user_auth>=2}">
				<input class="floating-right" type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/board/freeUpdateForm.do?free_num=${board.free_num}'"> 
				 <input class="floating-right" type="button" value="삭제" id="Delete_btn">
			</c:if>
			</div>
			<br>
			<hr width="100%" size="3">
		</div>
		</c:forEach>

		<div class="align-center">
			${fpage}
		</div>
		</c:if>

	</div>
	
	<!-- 동의보감 -->
	<div class="floating-right">
	<span style="float:left;">동의보감 total - ${theCnt} 건</span>	
		<c:if test="${theCnt==0}">
		<div>
				표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${theCnt > 0}">
		<c:forEach var="board" items="${tlist}">
		<div style="width:40%;margin:0 auto;">
		<div>
			<input type="hidden" id="the_num" value="${board.the_num}">
			<strong>Q</strong>  <a href="${pageContext.request.contextPath}/board/themeDetail.do?the_num=${board.the_num}">${board.the_title}</a>
			<span class="floating-right">${board.nick}</span><br><br>
		</div>
			<div class="floating-right">
			<c:if test="${!empty user_num && user_num==board.mem_num && user_auth>=2}">
				<input class="floating-right" type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/board/themeUpdateForm.do?the_num=${board.the_num}'"> 
				 <input class="floating-right" type="button" value="삭제" id="Delete_btn">
			</c:if>
			</div>
			<br>
			<hr width="100%" size="3">
		</div>
		</c:forEach>

		<div class="align-center">
			${tpage}
		</div>
		</c:if>

	</div>
	</div>
	
	</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>