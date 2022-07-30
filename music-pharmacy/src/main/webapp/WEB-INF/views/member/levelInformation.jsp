<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등급 안내</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/levelInformation.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPlaylist.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
<div class="page">
	<button class="prev_btn" onclick="location.href='myPage.do'"> &lt; 마이페이지</button>
	<h3 id="font-c">등급 안내</h3>
 
	<div class="mypage-div">
			<div class="levelInformation_profileImage">
			<c:if test="${empty member.photo}">
			<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo">
			</c:if>
			<c:if test="${!empty member.photo}">
			<img src="${pageContext.request.contextPath}/upload/${member.photo}" class="my-photo">
			</c:if>
			</div>
		</div>
		
		<div style="width:40%; float:left;">
			<p id="font-b"> <b>${member.nick}</b> 님의 등급은 <b>천민</b> </p><br> 
		</div>
		<table class="levelInformation_table">
			<tr>
				<th>등급</th>
				<th>포인트</th>
				<th>혜택</th>
			</tr>
			<tr>
				<td>왕</td>
				<td>100,000점 이상</td>
				<td>프로필 앞에 왕관 아이콘</td>
			</tr>
			<tr>
				<td>양반</td>
				<td>60,000 ~ 99,999점</td>
				<td>프로필 앞에 갓 아이콘</td>
			</tr>
			<tr>
				<td>평민</td>
				<td>60,000점 미만</td>
				<td>아이콘 없음</td>
			</tr>
		</table>
	</div>
</body>
</html>