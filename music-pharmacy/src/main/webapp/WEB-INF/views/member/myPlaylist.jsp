<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Playlist</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPlaylist.css" type="text/css">
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
			<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo">
			</c:if>
			<c:if test="${!empty member.photo}">
			<img src="${pageContext.request.contextPath}/upload/${member.photo}" class="my-photo">
			</c:if>
			</div>
		</div>
		
		<div style="width:40%; float:left;">
			<p id="font-b"> <b>${member.nick}</b> 님이 좋아요한 <b>Playlist</b> </p><br> 
			<c:forEach var="playlist" items="${boardList}">
			
			<div>
			
				<div class="floating-left">
					<c:if test="${empty music.mus_img}">
					<img src="${pageContext.request.contextPath}/images/album.png" width="100" height="100">
					</c:if>
					<c:if test="${!empty music.mus_img}">
					<img src="${pageContext.request.contextPath}/upload/${music.mus_img}" width="100" height="100">
					</c:if>
				</div>
				<div class="floating-left">
				<a href="${pageContext.request.contextPath}/board/themeDetail.do?the_num=${playlist.the_num}" id="font-b"><b>${playlist.mus_title}</b></a>
				<p>${playlist.mus_singer} - ${playlist.mus_album} </p> <br>
				</div>
			</div>
			<div style="clear:both;"></div>
			</c:forEach>
		</div>
</div>
</body>
</html>