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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/freeboard.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Freeboard.reply.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>
<div class="page">
 	<a href="${pageContext.request.contextPath}/board/freeBoard.do">저잣거리</a>
 	<p>자유게시판/음악추천받아요</p>

	<div class="content-main">
		<ul class="detail-info">
			<li>
				<c:if test = "${board.free_code==1}">
				자유게시판>
				</c:if>
				<c:if test = "${board.free_code==2}">
				음악추천받아요>
				</c:if>
				<h4>${board.free_title}</h4> 
				<c:if test="${!empty board.photo}">
				<img src="${pageContext.request.contextPath}/upload/${board.photo}" width="40" height="40" class="my-photo">
				</c:if>
				<c:if test="${empty board.photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
				</c:if>
				${board.nick}님
			</li> 
			<li>
			<div class="align-right">
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
				</c:if><br>
			    <c:if test="${!empty board.free_modify_date}">
				최근 수정일 : ${board.free_modify_date}<br>
				</c:if>
				작성일 : ${board.free_date}<br>
				view : ${board.free_hits}<br>
				<%-- 좋아요 --%>
				<img id="output_fav" src="${pageContext.request.contextPath}/images/fav01.gif" width="50">
				좋아요
				<span id="output_fcount"></span>
			</div>
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

		<!-- 댓글 시작 -->
		<div id="reply_div">
			<span class="re-title"><h4>댓글</h4></span>
			<form id="re_form">
				<input type="hidden" name="free_num" value="${board.free_num}" id="free_num">
				<textarea style="width:99%;" rows="3" cols="50" name="freply_content" 
				  id="freply_content" class="rep-content"  placeholder="댓글을 입력하세요."
				  <c:if test="${empty user_num}">disabled="disabled"</c:if>
				  ><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
				<c:if test="${!empty user_num}">
				<div id="re_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="re_second" class="align-right">
					<input type="submit" value="등록" >
				</div>
				</c:if>
			</form>
		</div>
		<!-- 댓글 목록 출력 시작 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/ajax-loader.gif">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글 끝 -->
		<div class="align-center">
		<c:if test="${!empty pre_free_board.free_num}">
		<div style="border-top:1px solid #eee;border-bottom:1px solid #eee;">
			<div>
				<span class="floating-left">이전글</span>
				<span><a href="freeDetail.do?free_num=${pre_free_board.free_num}">${pre_free_board.free_title}</a></span>
				<span class="floating-right">${pre_free_board.nick}님</span>
			</div>
		</div>
		</c:if>
		<c:if test="${!empty next_free_board.free_num}">
		<div style="border-top:1px solid #eee;border-bottom:1px solid #eee;">
			<div>
				<span class="floating-left">다음글</span>
				<span><a href="freeDetail.do?free_num=${next_free_board.free_num}">${next_free_board.free_title}</a></span>
				<span class="floating-right">${next_free_board.nick}님</span>
			</div>
		</div>
		</c:if>
		</div>
	</div>
</div>
</body>
</html>





