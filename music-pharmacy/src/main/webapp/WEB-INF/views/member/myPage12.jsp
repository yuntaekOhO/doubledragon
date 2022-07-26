<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My page</title>
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
		<hr style="border: solid 1px light-gray;">
		<div class="mypage-div">
			<ul>
				<li>
				<c:if test="${empty member.photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="120" height="120" class="my-photo">
				</c:if>
				<c:if test="${!empty member.photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.photo}" width="120" height="120" class="my-photo">
				</c:if>
				</li>
				<li>
					<div>
						<input type="file" id="photo" accept="image/gif,image/png,image/jpeg">
						<input type="button" value="등록" id="photo_submit">
						<input type="button" value="취소" id="photo_reset">
					</div>
				</li>
			</ul>
			<h3>비밀번호 수정</h3>
			<ul>
				<li>
					<input type="button" value="비밀번호 수정" onclick="location.href='modifyPasswordForm.do'">
				</li>
			</ul>
			<h3>회원탈퇴</h3>
			<ul>
				<li>
					<input type="button" value="회원탈퇴" onclick="location.href='deleteUserForm.do'">
				</li>
			</ul>
		</div>
		<div class="mypage-div">
			<h3>회원정보</h3>
			<ul>
				<li>이름 : ${member.name}</li>
				<li>전화번호 : ${member.cell}</li>
				<li>이메일 : ${member.email}</li>
				<li>우편번호 : ${member.zipcode}</li>
				<li>주소 : ${member.addr1} ${member.addr2}</li>
				<li>생일 : ${member.birthday}</li>
				<li>선호하는 음악 장르 : ${member.music}</li>
				<li>가입일 : ${member.reg_date}</li>
				<li>
					<input type="button" value="정보수정" onclick="location.href='modifyUserForm.do'">
				</li>
			</ul>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>