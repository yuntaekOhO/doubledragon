<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상소문</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/inquirystyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inquiryBoard.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
	<div>
	<div class="page">
 	<a href="${pageContext.request.contextPath}/board/inquiryBoard.do">상소문</a>
 	<p>자주 묻는 질문</p><br>
 	<hr>
 	<span style="float:left;">total - ${count} 건</span>
	<!-- 검색 -->
	<div class="search-bar">
		<form id="inq_search_form" action="inquiryBoard.do" method="get">
			<input type="search" class="input-search" id="keyword" name="keyword" value="${param.keyword}">
			<select id="keyfield" name="keyfield" style="float:right;height:35px;">
				<option value="1">질문</option>
				<option value="2">답변</option>
			</select>
			<input type="submit" class="input-search-submit" value="검색">
		</form>
	</div>
	<!-- 검색 끝 -->
	<%-- 관리자 전용 글작성 버튼 --%>
	<c:if test="${!empty user_num && user_auth==3}">
		<div>
			<form class="floating-right">
				<input type="button" value="글작성" onclick="location.href='writeInqForm.do'">
			</form>
		</div>
	</c:if>
	
	<div>
	</div>
	<div>
		<h3>자주 묻는 질문</h3>
		<c:if test="${count==0}">
		<div>
				표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<c:forEach var="board" items="${list}">
		<div style="width:90%;margin:0 auto;">
		<div>
			<input type="hidden" id="inq_num" value="${board.inq_num}">
			<strong>Q</strong>  <a href="inqDetail.do?inq_num=${board.inq_num}">${board.inq_question}</a>
			<span class="floating-right">${board.nick}</span><br><br>
			<strong>A</strong>  <a href="inqDetail.do?inq_num=${board.inq_num}">${board.inq_answer}</a>
			<c:if test="${empty board.inq_modify_date}">
			<span class="floating-right">작성일 ${board.inq_date}</span>
			</c:if>
			<c:if test="${!empty board.inq_modify_date}">
			<span class="floating-right">수정됨 ${board.inq_modify_date}</span>
			</c:if>
		</div>
			<div class="floating-right">
			<c:if test="${!empty user_num && user_auth==3}">
				<input type="button" value="수정" onclick="location.href='inquiryUpdateForm.do?inq_num=${board.inq_num}'"> 
				 <input type="button" value="삭제" id="inquiryDelete_btn">
			</c:if>
			</div>
			<br>
			<hr width="100%" size="3">
		</div>
		</c:forEach>

		<div class="align-center">
			${page}
		</div>
		</c:if>

	</div>
	
	</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>