<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>저잣거리 글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>
<div class="page">
 	<a href="${pageContext.request.contextPath}/board/freeBoard.do">저잣거리</a>
 	<p>자유게시판/음악추천받아요</p><br>
	<div class="content-main">
		<h2>글쓰기</h2>
		<form id="freeWrite_form" action="freeWrite.do" 
		   method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="free_code"></label>
					<select name="free_code" id="free_code">
					      <option value="1">자유게시판</option>
					      <option value="2">음악추천받아요</option>
					</select>
					<label for="free_title"></label>
					<input type="text" name="free_title" 
					      id="free_title" maxlength="100" placeholder="제목을 입력하세요.">
				</li>
				<br>
				<li>
					<label for="free_content"></label>
					<textarea rows="30" cols="185" name="free_content"
					     id="free_content" placeholder="내용을 입력하세요."></textarea>
				</li>
				<li>
					<label for="free_img"></label>
					<input type="file" name="free_img" 
					 id="free_img" 
					 accept="image/gif,image/png,image/jpeg">
				</li>
			    
			</ul>
			<div class="align-right">
				<input type="button" value="취소" 
				             onclick="location.href='freeBoard.do'">
				<input type="submit" value="등록">
			</div>
		</form>
	</div>
</div>
</body>
</html>

</body>
</html>