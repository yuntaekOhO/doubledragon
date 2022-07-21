<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header2 시작 -->
<div id="main2_logo">
	<div class="menu-profile">
			<img src="${pageContext.request.contextPath}/images/face.png" width="25" height="25" class="my-photo">
	</div>
	<div>
	<h1 class="align-left"> 
		<a href="${pageContext.request.contextPath}/main/main.do">음약방</a>
	</h1>
	</div>

<div id="main2_nav">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/board/notice.do">공지사항</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/free.do">저잣거리</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/theme.do">동의보감</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/inquiry.do">상소문</a>
		</li>

		
		<!-- 관리자로 로그인할경우 (몰라요) -->
		
		<!-- 로그인 된 경우 -->
		<c:if test="${!empty user_num}">
		<li>
			<a href="${pageContext.request.contextPath}/member/myPage.do">마이페이지</a>
		</li>
		</c:if>
		
		<!-- 로그인 안된경우 -->
		<c:if test="${empty user_num}">
		<li>
			<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
		</li>
		
		</c:if>
	</ul>
</div>
<!-- header2 끝 -->








