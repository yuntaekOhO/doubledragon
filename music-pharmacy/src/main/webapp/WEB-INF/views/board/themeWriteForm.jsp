<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동의보감 글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>

<div class="page">
	
	<h4>동의보감 글쓰기</h4>
		<form id="write_form" action="themeWrite.do" 
		   method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="the_title">제목</label>
					<input type="text" name="the_title" 
					      id="the_title" maxlength="50">
				</li>
				<li>
					<label for="the_content">내용</label>
					<textarea rows="5" cols="30" name="the_content"
					     id="the_content"></textarea>
				</li>
				<li>
					<label for="the_img">파일</label>
					<input type="file" name="the_img" 
					 id="the_img" 
					 accept="image/gif,image/png,image/jpeg">
				</li>
				<li>
					<label for="the_code">게시판 선택</label>
					<select name="the_code" id="the_code">

					      <option value="1">희喜</option>
					      <option value="2">노怒</option>
					      <option value="3">애哀</option>
					      <option value="4">락樂</option>
					</select>
				</li>
				<li>
					<label for="the_video">관련 영상</label>
					<input type="file" name="the_video" 
					 id="the_video" 
					 accept="video/mp4,video/avi,video/wmv">
				</li>
				<li>
					<label for="the_url">영상 링크</label>
					<input type="url" name="the_url" id="the_url">
				</li>
				
			</ul>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="목록" 
				             onclick="location.href='themeBoard.do'">
			</div>
		</form>
	<div>
	
	</div>
</div>
<div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>





