<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<footer>
		<div id="footer">
			<div id="logo">
				<p class="align-left" id="footersize">음약방</p>
				<p>국가가 허락한 유일한 보약, <b>음악</b> <br> <br><br></p>
				<p><b>creators</b> 크리에이터 | Double Dragon </p>
				<p><b>Developers</b> 김지호 오윤택 윤채영 이지수 정세령 </p>
				<hr style="border: solid 1px light-gray;">
				<p>@doubledragon Corp.</p>
			</div>
			
			<div id="category">
				<p id="footersize">고객센터</p>
				<p>kakao ID | musicpharmacy </p>
				<p>Mail | musicpharmacy@naver.com </p>
			</div>
			<div id="category">
				<p id="footersize">게시판</p>
				<a href="${pageContext.request.contextPath}/board/free.do">저잣거리</a> <br>
				<a href="${pageContext.request.contextPath}/board/theme.do">동의보감</a> <br>
				<a href="${pageContext.request.contextPath}/board/inquiry.do">상소문</a>
			</div>
		</div>
	</footer>
