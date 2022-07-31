<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
</head>
<body>
<!-- header2 시작 -->
<div>
	<div id="main2_logo">
		<div class="left">
				<img src="${pageContext.request.contextPath}/images/musicpharmacy.png" width="80" height="80" hspace="5">
		</div>
		<div>
		<h3 class="left"> 
			<a href="${pageContext.request.contextPath}/main/main.do">음약방</a>
		</h3>
		</div>
	</div>
	<div id="main2_nav">
		<ul class="myMenu">
			<li>
				<a href="${pageContext.request.contextPath}/board/noticeList.do"><b>어명이오</b></a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/board/freeBoard.do"><b>저잣거리</b></a>
			</li>
			<li class="menu-theme">
				<div><a href="${pageContext.request.contextPath}/board/themeBoard.do"><b>동의보감</b></a></div>
				<!-- 하위 메뉴 -->
				<ul class="submenu">
					<li><a href="${pageContext.request.contextPath}/board/themeBoard1.do">희</a></li>
					<li><a href="${pageContext.request.contextPath}/board/themeBoard2.do">노</a></li>
					<li><a href="${pageContext.request.contextPath}/board/themeBoard3.do">애</a></li>
					<li><a href="${pageContext.request.contextPath}/board/themeBoard4.do">락</a></li>
				</ul>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/board/inquiryBoard.do"><b>상소문</b></a>
			</li>
	
			
			<!-- 관리자로 로그인할경우 (몰라요) -->
			
			<!-- 로그인 된 경우 -->
			<c:if test="${!empty user_num}">
			<li class="menu-theme">
				<div><a href="${pageContext.request.contextPath}/member/myPage.do"><b>마이페이지</b></a></div>
				<!-- 하위 메뉴 -->
				<ul class="submenu">
					<li><a href="#">회원정보</a></li>
					<li><a href="#">My Playlist</a></li>
					<li><a href="#">My Point</a></li>
					<li><a href="#">작성한 글</a></li>
					<li><a href="#">작성한 댓글</a></li>
					<li><a href="#">등급안내</a></li>
				</ul>
			</li>
			</c:if>
			
			<!-- 로그인 안된경우 -->
			<c:if test="${empty user_num}">
			<li>
				<a href="${pageContext.request.contextPath}/member/loginForm.do"><b>로그인</b></a>
			</li>
			
			</c:if>
		</ul>
	</div>
</div>
<!-- header2 끝 -->

</body>
</html>






