<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/noticeDetail.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/noticeList.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/footer.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>
<div class="page">
	<a href="${pageContext.request.contextPath}/board/noticeList.do">어명이오</a>
 	<p>공지사항 게시판</p>
 	
 	<div class="content-main">
		<ul class="detail-info">
			<li>
				<h4 class="noticeDetail_title">${board.not_title}</h4><br>
				<c:if test="${!empty member.photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.photo}" width="40" height="40" class="my-photo">
				</c:if>
				<c:if test="${empty member.photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
				</c:if>
				${member.nick}
			</li> 
			<li>
			<div class="align-right">
				<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정,삭제 가능 --%>
					<c:if test="${user_num == board.mem_num}"> <!-- 관리자만 글 쓸수있으니깐 관리자만 수정삭제버튼 보여요 -->
					<input type="button" class="correction_btn" value="수정" 
					 onclick="location.href='noticeUpdateForm.do?not_num=${board.not_num}'">
					<input type="button" class="correction_btn" value="삭제" id="delete_btn">
					<script type="text/javascript">
						let delete_btn = document.getElementById('delete_btn');
						//이벤트 연결
						delete_btn.onclick=function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								location.replace('noticeDelete.do?not_num=${board.not_num}');
							}
						};
					</script>
					</c:if>
					<br>
					<c:if test="${!empty board.not_modify_date}">
					수정일 : ${board.not_modify_date}<br>
					</c:if>
					작성일 : ${board.not_date}
			</div>
			</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty board.not_img}">
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/upload/${board.not_img}" class="detail-img">
		</div>
		</c:if>
		<div class="noticeDetail_Content">
			${board.not_content}
		</div>
		<hr size="1" noshade="noshade" width="100%">

		<ul class="detail-sub">
			
		</ul>
		<!-- 이전 다음 글 -->
		<div class="noticeDetail_preBoard_nextBoard">
		<c:if test="${!empty pre_board.not_num}">
		<div style="border-top:1px solid #999;border-bottom:1px solid #999;">
			<div>
				<span class="floating-left">이전글</span>
				<span><a href="detail.do?not_num=${pre_board.not_num}" class="noticeDetail_pre_next_title">${pre_board.not_title}</a></span>
				<span class="floating-right">${pre_board.not_writer}</span>
			</div>
		</div>
		</c:if>
		<c:if test="${!empty next_board.not_num}">
		<div style="border-top:1px solid #999;border-bottom:1px solid #999;">
			<div>
				<span class="floating-left">다음글</span>
				<span><a href="detail.do?not_num=${next_board.not_num}" class="noticeDetail_pre_next_title">${next_board.not_title}</a></span>
				<span class="floating-right">${next_board.not_writer}</span>
			</div>
		</div>
		</c:if>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>





