<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상세정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myPage.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
<div class="page">
	<button class="prev_btn" onclick="location.href='memberList.do'"> &lt; 회원관리</button>
	<h3 id="font-c">회원정보</h3>
	
	<div class="mypage-div">
		<form id="modify_form" action="memberDetail.do" method="post">
				<input type="hidden" name="mem_num" value="${member.mem_num}">
				<div class="mypage-div5">
					<div class="profile-image3">
						<c:if test="${empty member.photo}">
						<img src="${pageContext.request.contextPath}/images/face.png" class="my-photo">
						</c:if>
						<c:if test="${!empty member.photo}">
						<img src="${pageContext.request.contextPath}/upload/${member.photo}" class="my-photo">
						</c:if>
					</div><br>
					<div class="button-align">
						<input type="file" id="photo" accept="image/gif,image/png,image/jpeg">
						<button class="photo_submit" id="photo_submit">등록</button>
						<button class="photo_reset" id="photo_reset">취소</button>
					</div>
				</div>
				<div class="mypage-div4">
					<ul>
					<li>
						<p id="font-c"><b>회원정보 수정</b></p>
					</li>
					<li>
						<label>권한</label>
						<c:if test="${member.auth!=3}">
						<input type="radio" name="auth" value="0" id="auth0"
							<c:if test="${member.auth==0}">checked</c:if>/>탈퇴
						<input type="radio" name="auth" value="1" id="auth1"
							<c:if test="${member.auth==1}">checked</c:if>/>정지
						<input type="radio" name="auth" value="2" id="auth2"
							<c:if test="${member.auth==2}">checked</c:if>/>일반
						</c:if>
						<c:if test="${member.auth==3}">
						<input type="radio" name="auth" value="3" id="auth3" checked/>관리자
						</c:if>
					</li>
					<li>
						<label>이름</label>
						${member.name}
					</li>
					<li>
						<label>닉네임</label>
						${member.nick}
					</li>
					<li>
						<label>이메일</label>
						${member.email}
					</li>
					<li>
						<label>전화번호</label>
						${member.cell}
					</li>
					<li>
						<label>우편번호</label>
						${member.zipcode}
					</li>
					<li>
						<label>주소</label>
						${member.addr1} ${member.addr2}
					</li>
					<li>
						<label>생년월일</label>
						${member.birthday}<br><br>
					</li>
					<li>
						<label>선호하는 음악 장르</label><br>
						<div id="align-center">
						${member.music}
						</div>
						<br>
					</li>
					<li>
						<label>가입경로</label><br>
						<div id="align-center">
						${member.route}
						</div>
					</li>
				</ul><br>
				
					<div>
					<div class="float2">	
					<input type="submit" class="correction_btn" value="완료">
					</div>
					</div>	
			</div>		
		</form>
		</div>
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>