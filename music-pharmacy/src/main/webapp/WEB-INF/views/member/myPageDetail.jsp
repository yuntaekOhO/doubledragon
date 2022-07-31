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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>
<div>
	<div class="page">
	<button class="prev_btn" onclick="location.href='myPage.do'"> &lt; 마이페이지</button>
		<h3 id="font-c">회원정보</h3>
		
		
		<div class="mypage-div">
		
				<div class="profile-image2 float">
				<c:if test="${empty member.photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="120" height="120" class="my-photo">
				</c:if>
				<c:if test="${!empty member.photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.photo}" width="120" height="120" class="my-photo">
				</c:if>
				</div>
		
			<div class="mypage-div2">
				<ul>
					<li>이름 : ${member.name}</li>
					<li>전화번호 : ${member.cell}</li>
					<li>이메일 : ${member.email}</li>
					<li>우편번호 : ${member.zipcode}</li>
					<li>주소 : ${member.addr1} ${member.addr2}</li>
					<li>생일 : ${member.birthday}</li><br>
					<li>가입일 : ${member.reg_date}</li>
					<li>선호하는 음악 장르 : ${member.music}</li>
					<li>가입경로 : ${member.route}</li>
				</ul>
			</div>
			<div class="mypage-div3">
				<button class="correction" onclick="location.href='modifyUserForm.do'">수정</button>
			</div>
	</div>
</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>