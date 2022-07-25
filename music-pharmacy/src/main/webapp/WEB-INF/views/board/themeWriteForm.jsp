<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<form id="write_form" action="write.do" 
		   method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="the_title">제목</label>
					<input type="text" name="title" 
					      id="title" maxlength="50">
				</li>
				<li>
					<label for="the_content">내용</label>
					<textarea rows="5" cols="30" name="content"
					     id="content"></textarea>
				</li>
				<li>
					<label for="the_img">파일</label>
					<input type="file" name="filename" 
					 id="filename" 
					 accept="image/gif,image/png,image/jpeg">
				</li>
				<li>
					<label for="the_code">게시판 선택</label>
					<br/>
					<select name="the_code" id="the_code">
					      <option value="">--게시판 선택--</option>
					      <option value="1">희喜</option>
					      <option value="2">노怒</option>
					      <option value="3">애哀</option>
					      <option value="4">락樂</option>
					</select>
				</li>
				<li>
					<label for="the_video">관련 영상</label>
					<input type="file" name="filename" 
					 id="filename" 
					 accept="image/gif,image/png,image/jpeg">
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="목록" 
				             onclick="location.href='list.do'">
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





