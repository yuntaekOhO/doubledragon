<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 댓글</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPage.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
<div class="page">
 		<h2><a href="${pageContext.request.contextPath}/member/myWritedList.do">내가 쓴 글</a></h2>
 	<hr>
 	<br>
 	<div style="width:100%;">
	<!-- 저잣거리 
	<div class="floating-left" style="width:48%;">
		<span style="float:left;"><b>저잣거리</b> total - ${freeCnt} 건</span><br>
		<c:if test="${freeCnt==0}">
		<div>
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${freeCnt > 0}">
		<c:forEach var="board" items="${flist}">
		<div style="margin:0 auto;">
			<div>
				<input type="hidden" id="free_num" value="${board.free_num}">
				<a href="${pageContext.request.contextPath}/board/freeDetail.do?free_num=${board.free_num}">${board.free_title}</a>
				<span class="floating-right">${board.nick}</span><br><br>
			</div>
			<div class="floating-right">
				<c:if test="${!empty user_num && user_num==board.mem_num && user_auth>=2}">
				<input type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/board/freeUpdateForm.do?free_num=${board.free_num}'"> 
				<input type="button" value="삭제" id="fDelete_btn">
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
	<div class="floating-right" style="width:48%;">
		<span style="float:left;"><b>동의보감</b> total - ${theCnt} 건</span>	<br>
		<c:if test="${theCnt==0}">
		<div>
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		
		<c:if test="${theCnt > 0}">
		<c:forEach var="reboard" items="${trelist}" varStatus="status">
		<div style="margin:0 auto;">
			<div>
				<input type="hidden" id="the_num" value="${reboard.the_num}">
				<br>
				<%-- 댓글 단 게시글 제목 --%>
				${tBoardList[status.index].the_title}
				<%-- 댓글 내용 --%>
				<a href="${pageContext.request.contextPath}/board/themeDetail.do?the_num=${reboard.the_num}">${reboard.treply_content}</a>
				<c:if test="${empty reboard.treply_modify_date}">
				<span class="floating-right">작성일 ${reboard.treply_date}</span>
				</c:if>
				<c:if test="${!empty reboard.treply_modify_date}">
				<span class="floating-right">수정일 ${reboard.treply_modify_date}</span>
				</c:if>
				<br>
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


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>