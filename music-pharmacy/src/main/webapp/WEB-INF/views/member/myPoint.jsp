<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Point</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPage.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<div>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>
<div class="page">
	<button class="prev_btn" onclick="location.href='myPage.do'"> &lt; 마이페이지</button>
	<h3 id="font-c">My Point</h3>
	<div class="total">
	<div class="profile">
			<div class="profile-image2 float">
				<c:if test="${empty member.photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo">
				</c:if>
				<c:if test="${!empty member.photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.photo}" class="my-photo">
				</c:if>
			</div>
		</div>
		
		<div class="mypage-div float">
			<p id="font"> <b>${member.nick}</b> 님의 포인트는 ${member.point} 입니다.</p>
		</div> 
	</div>
	<div style="clear:both;"></div>
	<div class="margin">
			<table class="levelInformation_table">
				<tr>
					<th>날짜</th>
					<th>내용</th>
					<th>누적 포인트</th>
				</tr>
				<c:forEach var="list" items="${list}" varStatus="status">
					<tr>
						<td>${list.board_date}</td>
						<td>글 작성으로 100포인트 적립</td>
						<td>${member.point}</td>
					</tr>
				</c:forEach>
				
			</table> <br><br><br>
	</div>
</div>
</body>
</html>