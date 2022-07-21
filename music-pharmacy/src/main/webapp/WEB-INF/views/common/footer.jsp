<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- footer 시작 -->
<style type="text/css">
	h2{
		line-height:0.5em;
		font-color: white;
	}
	p{
		line-height:0.5em;
		font-size:12px;
	}
	a{
		font-size:12px;
		
	}
	#logo{
		margin-left:50px;
		float:left;
	}
	#category{
		float:right;
		margin-right:50px;
	}
	#footer{
		background-color:#d4c09f;
	}
</style>
<div class="footer">
	<div id="logo">
		<h2 class="align-left">음약방</h2>
		<p>국가가 허락한 유일한 보약, <b>음악</b> <br> <br><br></p>
		<p><b>creators</b> 크리에이터 | Double Dragon </p>
		<p><b>Developers</b> 김지호 오윤택 윤채영 이지수 정세령 </p>
		<hr style="border: solid 1px light-gray;">
		<p>@doubledragon Corp.</p>
	</div>
	
	<div id="category">
		<ul>
			<li>
				<h4>고객센터</h4>
				<p>kakao ID | musicpharmacy </p>
				<p>Mail | musicpharmacy@naver.com </p>
				
			</li>
		</ul>
	</div>
	<div id="category">
		<ul>
			<li>
				<h4>게시판</h4>
				<a href="${pageContext.request.contextPath}/board/free.do">저잣거리</a> <br>
				<a href="${pageContext.request.contextPath}/board/theme.do">동의보감</a> <br>
				<a href="${pageContext.request.contextPath}/board/inquiry.do">상소문</a>
			</li>
		</ul>
	</div>
</div>