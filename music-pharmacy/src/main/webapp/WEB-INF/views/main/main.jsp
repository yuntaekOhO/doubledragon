<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>

<div>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div style="margin: 30px; padding: 5px; text-align: center; border-radius: 50px; background-color: #684f3c; width: 90px; color: white">
			<h5>BEST보약</h5>
	</div>
	<div style="float: left; width: 50%; padding:10px;">
		<a>
		<div style="float: left; width: 33%; text-align:center">
			<img src=".jpg" width="120", height="120" vspace="10" hspace="20"/>
			<br>
			이달의 소리꾼1
			<br>
			1
		</div>
		</a>
		
		<a>
		<div style="float: left; width: 33%; text-align:center">
			<img src=".jpg" width="120", height="120" vspace="10" hspace="20"/>
			<br>
			이달의 소리꾼2
			<br>
			2
		</div>
		</a>
		
		<a>
			<div style="float: left; width: 33%; text-align:center">
			<img src=".jpg" width="120" height="120" vspace="10" hspace="20"/>
			<br>
			이달의 소리꾼3
			<br>
			3
		</div>
		</a>
	</div>
	
	<div style="float: right; width: 40%; padding:15px; background-color: #684f3c; color: white; line-height: 150%">
		<h5>어명이오</h5>
		<div style="width: 90%; height:2px; background-color: white"></div>
		<br>
		<a>text1</a><br>
		<a>text2</a><br>
		<a>text3</a><br>
		<a>text4</a><br>
		<a>text5</a>
	</div>
	
	<div style="float: left; width: 50%; padding:10px;">
		<a>
		<div style="float: left; width: 33%; text-align:center">
			<img src=".jpg" width="120", height="120" vspace="10" hspace="20"/>
			<br>
			이달의 명의1
			<br>
			1
		</div>
		</a>
		
		<a>
		<div style="float: left; width: 33%; text-align:center">
			<img src=".jpg" width="120", height="120" vspace="10" hspace="20"/>
			<br>
			이달의 명의2
			<br>
			2
		</div>
		</a>
		
		<a>
		<div style="float: left; width: 33%; text-align:center">
			<img src=".jpg" width="120" height="120" vspace="10" hspace="20"/>
			<br>
			이달의 명의3
			<br>
			3
		</div>
		</a>
	</div>
	
	<div style="float: right; width: 20%; height: 190px; padding:15px; background-color: #d0c2a8; color: white; line-height: 150%">
		<h5>고객센터</h5>
		<div style="width: 90%; height:2px; background-color: white; font-size: small"></div>
		<h5>kakao ID |<br>musicpharamacy
		<br>
		Mail | musicpharamacy@naver.com
		</h5>
	</div>
	
	<div style="float: right; width: 16.65%; height: 190px; padding:15px; color: white; background-color: #e3dbcc; line-height: 150%">
		
	</div>
</div>
</body>
</html>



