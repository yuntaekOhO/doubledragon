<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등급 안내</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPage.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
<div class="page">
	<button class="prev_btn" onclick="location.href='myPage.do'"> &lt; 마이페이지</button>
	<h3 id="font-c">등급 안내</h3>
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
			<c:if test="${member.mem_level == 0}">
			<p id="font"> <b>${member.nick}</b> 님의 등급은 평민입니다.</p>
			</c:if>
			<c:if test="${member.mem_level == 1}">
			<p id="font"> <b>${member.nick}</b> 님의 등급은 양반입니다.</p>
			</c:if>
			<c:if test="${member.mem_level == 2}">
			<p id="font"> <b>${member.nick}</b> 님의 등급은 왕입니다.</p>
			</c:if>
		</div> 
	</div>
	<div style="clear:both;"></div>
		<div class="margin">
			<table class="levelInformation_table">
					<tr>
						<th>등급</th>
						<th>포인트</th>
					</tr>
					<tr>
						<td>왕</td>
						<td>10,000점 이상</td>
					</tr>
					<tr>
						<td>양반</td>
						<td>6,000 ~ 9,999점</td>
					</tr>
					<tr>
						<td>평민</td>
						<td>6,000점 미만</td>
					</tr>
				</table> <br><br><br>
			<div class="align_center">
				<div class="float">
				<p id="font-d"> <b>𖤐 등급이란? </b></p>
				<p> 회원들의 활동량에 따라서 포인트를 획득하여 가질 수 있는 회원님의 등급을 말합니다.</p>
				<p> 글작성, 댓글 작성등의 활동을 통하여 회원들이 획득한 점수를 통해 회원 등급이 결정됩니다.</p>
				<p> 회원님의 등급 업데이트는 매주 월요일 주 1회 업데이트됩니다.</p>
				<p> <b>@ 주의 @ </b> 정지회원의 포인트는 전부 몰수됩니다! </p>
				</div>
				
				<div class="banner-img">
					<img src="${pageContext.request.contextPath}/images/banner_main.png">
				</div>
			</div>
		</div>
	</div>
<div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>