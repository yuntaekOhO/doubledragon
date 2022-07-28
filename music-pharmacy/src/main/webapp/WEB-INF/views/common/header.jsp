<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header 시작 -->
<div class="header">
<div class="banner">
	<h1>
		<img src="${pageContext.request.contextPath}/images/face.png" width="50px">
		<a href="${pageContext.request.contextPath}/main/main.do">음약방</a>
	</h1>
	<div class="header-content">
		<p>
			국가가 허락한 유일한 보약,<b> 음악</b><br>
			끊임없이 멜로디를 찾고,듣고,부르는 우리들<br>
			Music is MyLife..★의 삶을 <strong>음약방</strong>을 통해 만나보세요
		</p>
		<c:if test="${empty user_num}">
		<div>
		<span><a href="${pageContext.request.contextPath}/member/registerUserForm.do">sign up for free</a></span>
		</div>
		</c:if>
	</div>
</div>
<div class="banner-img">
	<img src="${pageContext.request.contextPath}/images/face.png">
</div>
<div class="banner-btns">
	<%-- 로그인 안된 경우 --%>
	<c:if test="${empty user_num}">
	<div class="btn-signup align-center">
		<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
	</div>
	<div class="btn-login align-center">
		<a  href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
	</div>
	</c:if>
	<%-- 로그인 된 경우 --%>
	<c:if test="${!empty user_num}">
	<li class="menu-logout">
		[<span>${user_id}</span>]
		<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
	</li>
	</c:if>
</div>
<div id="main_nav">
	<ul class="myMenu">
		<li>
			<a href="${pageContext.request.contextPath}/board/noticeList.do">공지사항</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/freeBoard.do">저잣거리</a>
		</li>
		<li class="menu-theme">
			<div><a href="${pageContext.request.contextPath}/board/themeBoard.do">동의보감</a></div>
			<ul class="submenu">
            	<li><a>희</a></li>
            	<li><a>노</a></li>
            	<li><a>애</a></li>
            	<li><a>락</a></li>
       		</ul>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/inquiryBoard.do">상소문</a>
		</li>
		<%-- 로그인 된 경우 --%>
		<c:if test="${!empty user_num && user_auth == 2}">
		<li>
			<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
		</li>
		</c:if>
		<%-- 관리자 로그인 된 경우 --%>
		<c:if test="${!empty user_num && user_auth == 3}">
		<li>
			<a href="${pageContext.request.contextPath}/member/memberList.do">회원관리</a>
		</li>
		</c:if>
		<!-- 메뉴바 우측 검색창 -->
		<li class="other-li floating-right">
			<div class="search">
				<form>
					<input type="button" value="O" id=""><input type="text" name="" id="">
				</form>
			</div>
		</li>
		</ul>
		
		<!--

		<c:if test="${!empty user_num && !empty user_photo}">
		<li class="menu-profile">
			<img src="${pageContext.request.contextPath}/upload/${user_photo}" width="25" height="25" class="my-photo">
		</li>
		</c:if>
		
		<c:if test="${!empty user_num && empty user_photo}">
		<li class="menu-profile">
			<img src="${pageContext.request.contextPath}/images/face.png" width="25" height="25" class="my-photo">
		</li>
		</c:if>
		-->
</div>
</div>
<!-- header 끝 -->
<!-- 커밋용 -->





