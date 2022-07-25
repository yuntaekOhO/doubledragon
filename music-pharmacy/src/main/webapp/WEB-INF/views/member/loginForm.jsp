<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginstyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>

<div class='wrap'>
  Login
    <form id="login_form" action="login.do" method="post">
        <input type='text' name="id" id='id' placeholder='Username'>
        <input type='password' name="passwd" id='passwd' placeholder='Password'>
   
    <div>
	  	<button class='main'  onclick="location.href='${pageContext.request.contextPath}/main/main.do'">Main</button> 
	  	<button class='login' type="submit">LOG IN</button>
  	</div>
  	</form>
</div>

</body>
</html>