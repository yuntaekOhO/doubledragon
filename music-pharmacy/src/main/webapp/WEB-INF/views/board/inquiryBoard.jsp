<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상소문</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
	<div>
	<div class="page">
 	<a href="${pageContext.request.contextPath}/board/inquiryBoard.do">상소문</a>
 	<p>자주 묻는 질문 게시판</p><br>
 	<p style="float:left;">${count} 건</p>
	<!-- 검색 -->
	<div class="pull-left">
		<div class="search-bar">
			<form>
				<select name="keyfield">
					<option value="1">제목</option>
					<option value="2">질문</option>
					<option value="3">답변</option>
				</select>
				<input type="search" class="input-search"> <input
					type="submit" class="input-search-submit" value="검색">
			</form>
		</div>
	</div>
	<!-- 검색 끝 -->
	<%-- 관리자 전용 글작성 버튼 --%>
	<c:if test="${!empty user_num && user_auth==3}">
		<div>
			<form class="floating-right" action="writeInqForm.do">
				<input type="submit" value="글작성">
			</form>
		</div>
	</c:if>
	
	<div class="floating-clear">
	</div>
	<div>
		<h3>자주 묻는 질문</h3>
		<c:if test="${count==0}">
		<div>
				표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<div style="padding:0 70px">
			<ul>
			<c:forEach var="board" items="${list}">
				<li>
					<a>${board.inq_title}</a>
				</li>
				<li>
					<strong>Q</strong> <span><a>${board.inq_question}</a></span><br><br>
				</li>
				<li>
					<strong>A</strong> <span><a>${board.inq_answer}</a></span><br><br>
				</li>
			</c:forEach>
			</ul>
		</div>
		</c:if>
		<hr width="90%">
		
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	</div>
	
	<div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>

</div>
	</div>
</body>
</html>