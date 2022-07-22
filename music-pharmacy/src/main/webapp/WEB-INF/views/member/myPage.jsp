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
	<div>
		<h2>마이페이지</h2>
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
						<input type="button" value="수정" id="photo_btn">
					</div>
					<div>
						<input type="file" id="photo" accept="image/gif,image/png,image/jpeg">
						<input type="button" value="등록" id="photo_btn">
						<input type="button" value="취소" id="photo_btn">
					</div>
				</li>
			</ul>
			
			
		</div>
	</div>
</div>
</body>
</html>