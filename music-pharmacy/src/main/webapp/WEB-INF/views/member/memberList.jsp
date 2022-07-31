<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>

<div class="page">
 	<a href="${pageContext.request.contextPath}/board/freeBoard.do">저잣거리</a>
 	<p>회원목록</p>
 	<p style="float:left;">총 ${count}건</p>
	<!-- 검색 -->
	<div class="search-bar">
		<form id="inq_search_form" action="memberList.do" method="get">
			<input type="search" class="input-search" id="keyword" name="keyword" value="${param.keyword}">
			<select id="keyfield" name="keyfield" style="float:right;height:35px;">
				<option value="1">ID</option>
				<option value="2">닉네임</option>
				<option value="3">권한</option>
			</select>
			<input type="submit" class="input-search-submit" value="검색">
		</form>
	</div>
	<!-- 검색 끝 -->
      <!-- 로그인되어있으면 글쓰기가보임, 나중에 관리자 로그인시에만으로 바꿔야함 -->
 	    <div class="align-right">

		</div>

 	<c:if test="${count == 0}">
		<div>
			표시할 회원이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		
		<!-- 회원목록 시작 -->
		<hr size="1" noshade="noshade" width="100%">
		<table class="free_table" cellpadding="10" width="100%">
			<tr style="border-bottom:1px solid #423207;">
				<th>ID</th>
				<th>닉네임</th>
				<th>이메일</th>
				<th>권한</th>
				<th>가입일</th>
			</tr>
		<c:forEach var="member" items="${list}">
			<tr style="text-align:center;border-bottom:1px solid #423207;">
				<td class="free_theme" width="15%">
					${member.id}
				</td>
				<td width="15%"><a href="memberDetailForm.do?mem_num=${member.mem_num}">${member.nick}</a></td>
				<td width="15%">${member.email}</td>
				<td width="15%">
				<c:if test="${member.auth==0}">
				<span style="color:brown;">탈퇴</span>
				</c:if>
				<c:if test="${member.auth==1}">
				<span style="color:red;">정지</span>
				</c:if>
				<c:if test="${member.auth==2}">
				<span>일반</span>
				</c:if>
				<c:if test="${member.auth==3}">
				<span style="color:blue;">관리자</span>
				</c:if>
				</td>
				<td width="20%">${member.reg_date}</td>
			</tr>
			</c:forEach>
		</table>
		<hr size="1" noshade="noshade" width="100%">
		<!-- 게시글 끝 -->
		<div class="align-center">
			${page}
		</div>
		</c:if>
 	  
	
</div>
<div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>