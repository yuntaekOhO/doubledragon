<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.fav.js"></script>
</head>
<body>

<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
	<div class="content-main">
		<h2>${board.free_title}</h2>
		<ul class="detail-info">
			<li>
				<c:if test="${!empty board.free_img}">
				<img src="${pageContext.request.contextPath}/upload/${board.free_img}" width="40" height="40" class="my-photo">
				</c:if>
				<c:if test="${empty board.free_img}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
				</c:if>
			</li> 
			<li>
				작성자 : ${board.free_writer}<br>
				<c:if test = "${board.free_code==1}">
				게시판 종류 : 자유게시판
				</c:if>
				<c:if test = "${board.free_code==2}">
				게시판 종류 : 음악추천받아요
				</c:if><br>
				조회 : ${board.free_hits}
			</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty board.free_img}">
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/upload/${board.free_img}" class="detail-img">
		</div>
		</c:if>
		<p>
			${board.free_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<%-- 좋아요 --%>
				<img id="output_fav" src="${pageContext.request.contextPath}/images/fav01.gif" width="50">
				좋아요
				<span id="output_fcount"></span>
			</li>
			<li>
				<c:if test="${!empty board.free_modify_date}">
				최근 수정일 : ${board.modify_date}
				</c:if>
				작성일 : ${board.free_date}
				<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정,삭제 가능 --%>
				<c:if test="${user_num == board.mem_num}">
				<input type="button" value="수정" 
				 onclick="location.href='freeUpdateForm.do?free_num=${board.free_num}'">
				<input type="button" value="삭제" id="delete_btn">
				<script type="text/javascript">
					let delete_btn = document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick=function(){
						let choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('freeDelete.do?free_num=${board.free_num}');
						}
					};
				</script>
				</c:if>
			</li>
		</ul>
		<!-- 댓글 시작 -->
		<div id="reply_div">
			<span class="re-title">댓글 달기</span>
			<form id="re_form">
				<input type="hidden" name="free_num" value="${board.free_num}" id="free_num">
			</form>
		</div>
		<!-- 댓글 끝 -->
	</div>
</div>
</body>
</html>





