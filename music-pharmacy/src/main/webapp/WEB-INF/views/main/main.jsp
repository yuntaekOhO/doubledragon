<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header1.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/noticeList.js"></script>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div id="content">
	
	<div class="best-boyak">
			<span>BEST보약</span>
	</div>
	
	<div style="float:left;width:50%;">
	
	<div class="month-rank">
		<div class="rank-detail">
			<a>
				<img src=".jpg" width="120" height="120" vspace="10" hspace="20"/>
				<br>
				이달의 소리꾼1<!-- 이름 -->
				<br>
				1<!-- 순위 -->
			</a>
		</div>
		
		<div class="rank-detail">
			<a>
				<img src=".jpg" width="120" height="120" vspace="10" hspace="20"/>
				<br>
				이달의 소리꾼2
				<br>
				2
			</a>
		</div>
		
		<div class="rank-detail">
			<a>
				<img src=".jpg" width="120" height="120" vspace="10" hspace="20"/>
				<br>
				이달의 소리꾼3
				<br>
				3
			</a>
		</div>
	</div>
	
	<div class="month-rank">
		<div class="rank-detail">
		<a>
			<img src=".jpg" width="120" height="120" vspace="10" hspace="20"/>
			<br>
			이달의 명의1
			<br>
			1
		</a>
		</div>
		
		<div class="rank-detail">
		<a>
			<img src=".jpg" width="120" height="120" vspace="10" hspace="20"/>
			<br>
			이달의 명의2
			<br>
			2
		</a>
		</div>
		
		<div class="rank-detail">
		<a>
			<img src=".jpg" width="120" height="120" vspace="10" hspace="20"/>
			<br>
			이달의 명의3
			<br>
			3
		</a>
		</div>
	</div>
	
	</div>
	
	<div style="float:right;width:50%;">
	<div class="mini-notice">
		<h5>어명이오</h5>
		<div></div>
		<ul>
		<li><a href="http://localhost:8080/music-pharmacy/board/detail.do?not_num=201">테스트1</a></li>
		<li><a href="http://localhost:8080/music-pharmacy/board/detail.do?not_num=201">테스트2</a></li>
		<li><a href="http://localhost:8080/music-pharmacy/board/detail.do?not_num=201">테스트3</a></li>
		<li><a href="http://localhost:8080/music-pharmacy/board/detail.do?not_num=201">테스트4</a></li>
		<li><a href="http://localhost:8080/music-pharmacy/board/detail.do?not_num=201">테스트5</a></li>		
		</ul>
	</div>
	<div class="mini-content-square1">
		<h5>고객센터</h5>
		<div></div>
		<h5>kakao ID |<br>musicpharamacy
		<br>
		Mail | musicpharamacy@naver.com
		</h5>
	</div>
	
	<div class="mini-content-square2">
		<h5>고객센터</h5>
		<div></div>
		<h5>kakao ID |<br>musicpharamacy
		<br>
		Mail | musicpharamacy@naver.com
		</h5>
	</div>
	</div>
	
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



