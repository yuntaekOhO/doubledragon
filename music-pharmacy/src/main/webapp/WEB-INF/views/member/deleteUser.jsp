<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check}">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
<div>
	<div class="content-main">
		<h3>회원탈퇴 완료</h3>
		<div class="result-display">
			회원탈퇴가 완료되었습니다.
			<p>
			<input type="button" value="메인" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</div>
</div>
</body>
</html>
</c:if>
<c:if test="${!check}">
	<script>
		alert('입력한 정보가 정확하지 않습니다!');
		history.go(-1);
	</script>
</c:if>