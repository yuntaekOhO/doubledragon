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
	<div class="page">
 		<h2><a href="${pageContext.request.contextPath}/member/myWritedList.do">내가 쓴 글</a></h2>
 	<hr>
 	<br>
 	<div style="width:100%;block-size:fit-content;display:inline-block;">
 	<div style="height:30px;">
	<span class="floating-left"><b>저잣거리</b> total - ${freeCnt} 건</span>
	<span class="floating-right"><b>동의보감</b> total - ${theCnt} 건</span>
	</div>
	<!-- 저잣거리 -->
	<div class="floating-left" style="width:48%;display:inline-block;block-size:fit-content;">
		<c:if test="${freeCnt==0}">
		<div>
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${freeCnt > 0}">
		<c:forEach var="board" items="${flist}">
		<div style="margin:0 auto;block-size:fit-content;position:relative;top:50px;">
			<div class="floating-left">
				<c:if test="${board.free_code==1}">
					[자유]
				</c:if>
				<c:if test="${board.free_code==2}">
					[추천]
				</c:if>
			</div>
			<div style="float:left;position:relative;left:20%;display:inline-block;">
				<input type="hidden" id="free_num" value="${board.free_num}">
				<span><a href="${pageContext.request.contextPath}/board/freeDetail.do?free_num=${board.free_num}">${board.free_title}</a></span>
			</div>
			<div style="float:right;display:inline-block;postion:relative;left:50%;">
				<c:if test="${empty board.free_modify_date}">
				<span>작성일 ${board.free_date}</span>
				</c:if>
				<c:if test="${!empty board.free_modify_date}">
				<span>수정됨 ${board.free_modify_date}</span>
				</c:if>
			</div>
			<div style="clear:both;"></div>
			<br>
			<hr width="100%" size="3">
		</div>
		</c:forEach>
		<div class="align-center" style="display:block;">
			${fpage}
		</div>
		</c:if>
	</div>
	
	<!-- 동의보감 -->
	<div class="floating-right" style="width:48%;display:inline-block;block-size:fit-content;">
		<c:if test="${theCnt==0}">
		<div>
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		
		<c:if test="${theCnt > 0}">
		<c:forEach var="board" items="${tlist}">
		<div style="margin:0 auto;block-size:fit-content;position:relative;top:50px;">
			<div class="floating-left">
				<c:if test="${board.the_code==1}">
					[희 喜]
				</c:if>
				<c:if test="${board.the_code==2}">
					[노 怒]
				</c:if>
				<c:if test="${board.the_code==3}">
					[애 愛]
				</c:if>
				<c:if test="${board.the_code==4}">
					[락 樂]
				</c:if>
			</div>
			<div>
				
			</div>
			<div style="float:left;position:relative;left:20%;display:inline-block;">
				<input type="hidden" id="the_num" value="${board.the_num}">
				<span><a href="${pageContext.request.contextPath}/board/themeDetail.do?the_num=${board.the_num}">${board.the_title}</a></span>
			</div>
			<div style="float:right;display:inline-block;postion:relative;left:50%;">
				<c:if test="${empty board.the_modify_date}">
				<span>작성일 ${board.the_date}</span>
				</c:if>
				<c:if test="${!empty board.the_modify_date}">
				<span>수정됨 ${board.the_modify_date}</span>
				</c:if>
			</div>
			<div style="clear:both;"></div>
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