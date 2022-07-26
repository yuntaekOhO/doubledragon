<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPage.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>
<div>
	<div class="page">
		<a href="${pageContext.request.contextPath}/member/myPage.do">마이페이지</a>
		<hr style="border: solid 1px light-gray;"><br>
	
		 <div class="profile-image">
		 	
				<c:if test="${empty member.photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo">
				</c:if>
				<c:if test="${!empty member.photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.photo}" class="my-photo">
				</c:if>
			
		</div>
		
		<div class="mypage-div">
			<p> <b>${member.nick}</b>님의 등급은 ${member.mem_level}입니다.</p>
		</div> 
		
		<div class="mypage-infomation">
			<a href="${pageContext.request.contextPath}/member/modifyUserForm.do">회원정보</a>
			<p>회원이신 환자분의 개인 정보를 <br> 저장하고 관리하는 공간입니다.</p>
		</div>
		
		<div class="mypage-infomation">
			<a>My playlist</a>
			<p>환자분이 좋아요 누른 음악들의 <br> 리스트를 확인할 수 있는 공간입니다.
			</p>
		</div>
		
		<div class="mypage-infomation">
			<a>My point</a>
			<p>환자분의 포인트 적립/차감 <br> 내역을 확인할 수 있는 공간입니다.</p>
		</div>
		
		<div class="mypage-infomation">
			<a>작성한 글</a>
			<p>환자분이 작성한 글의 목록을<br> 확인할 수 있는 공간입니다.</p>
		</div>
		
		<div class="mypage-infomation">
			<a>작성한 댓글</a>
			<p>환자분이 작성한 댓글의 목록을 <br> 확인할 수 있는 공간입니다.</p>
		</div>
		
		<div class="mypage-infomation">
			<a>등급안내</a>
			<p>각 등급의 조건 및 혜택에 <br> 대해 확인할 수 있는 공간입니다.</p>
		</div>
		
	</div>
		
		
</div>
	
		
		
			
	
	
	
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>