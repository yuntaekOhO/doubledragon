<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header 시작 -->
<div style="display:inline-block;position:relative;top:-70px;left:10px">
	<h1 style="width:200px;">
		<img src="${pageContext.request.contextPath}/images/face.png" width="50px">
		<a href="${pageContext.request.contextPath}/main/main.do">음약방</a>
	</h1>
	<div style="margin-left:100px;">
		<p>
			국가가 허락한 유일한 보약,<b style="font-size:2em;"> 음악</b><br>
			끊임없이 멜로디를 찾고,듣고,부르는 우리들<br>
			Music is MyLife..★의 삶을 <b>음약방</b>을 통해 만나보세요
		</p>
		<div style="border-radius:3px;background:orange;width:150px;height:30px;">
		<span style="padding-left:20px;text-align:center;color:white;">sign up for free</span>
		</div>
	</div>
</div>
<div style="display:inline-block;margin:50px 0 50px 110px;">
	<img src="${pageContext.request.contextPath}/images/face.png">
</div>
<div style="margin:20px 20px 0 20px;display:inline-block;float:right;">
	<div style="display:inline-block;border-radius:5px;background:red;">
		회원가입
	</div>
	<div style="display:inline-block;border-radius:5px;background:pink;">
		로그인
	</div>
</div>
<div id="main_nav">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/board/list.do">공지사항</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/list.do">저잣거리</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/list.do">동의보감</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/list.do">상소문</a>
		</li>
		<c:if test="${!empty user_num && user_auth == 2}">
		<li>
			<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
		</li>
		</c:if>
		<li style="padding:0 0 0 150px;">
			<div class="align-right">
				<input type="button" value="돋보" id="">
			</div>
		</li>
		<li style="width:250px;padding:0;">
			<div class="align-right" style="padding:0 0 0 0;">
				<input type="text" name="" id="" style="width:100%">
			</div>
		</li>
		
		<!--
		<c:if test="${!empty user_num && user_auth == 3}">
		<li>
			<a href="${pageContext.request.contextPath}/member/memberList.do">회원관리</a>
		</li>
		</c:if>
		
		<c:if test="${!empty user_num && user_auth == 2}">
		<li>
			<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
		</li>
		</c:if>
		-->
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
		<!--
		<c:if test="${!empty user_num}">
		<li class="menu-logout">
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		</li>
		</c:if>
		
		<c:if test="${empty user_num}">
		<li>
			<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
		</li>
		</c:if>
		 -->
	</ul>
</div>
<!-- header 끝 -->





