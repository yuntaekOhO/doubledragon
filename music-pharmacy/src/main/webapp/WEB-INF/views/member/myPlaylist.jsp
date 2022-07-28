<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Playlist</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPage.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>

<div class="page">
	<button class="prev_btn" onclick="location.href='myPage.do'"> &lt; 마이페이지</button>
		<h3 id="font-c">My Playlist</h3>
		
		<div class="mypage-div">
			<div class="profile-image2 float">
			<c:if test="${empty member.photo}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="120" height="120" class="my-photo">
			</c:if>
			<c:if test="${!empty member.photo}">
			<img src="${pageContext.request.contextPath}/upload/${member.photo}" width="120" height="120" class="my-photo">
			</c:if>
			</div>
		</div>
		
		<div>
			<p> ${member.nick} 님이 좋아요한 Playlist </p>
		</div>
		<table>
			
			<c:forEach var="playlist" items="${boardList}">
			<tr class="musicform-align">
				<td class="the-img">
					<div>
						<c:if test="${empty music.mus_img}">
						<img src="${pageContext.request.contextPath}/images/album.png" width="100" height="100">
						</c:if>
						<c:if test="${!empty music.mus_img}">
						<img src="${pageContext.request.contextPath}/upload/${music.mus_img}" width="100" height="100">
						</c:if>
					</div>
				</td>
				<td>${music.mus_title}</td><br>
				<td>${music.mus_singer} - ${music.mus_album} </td>
			</tr>
			</c:forEach>
			
		</table>
</div>
</body>
</html>