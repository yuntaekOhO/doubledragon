<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>저잣거리 글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
	<div class="content-main">
		<h2>게시판 글쓰기</h2>
		<form id="freeWrite_form" action="freeWrite.do" 
		   method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="free_title">제목</label>
					<input type="text" name="free_title" 
					      id="free_title" maxlength="50">
				</li>
				<li>
					<label for="free_writer">작성자</label>
					<input type="text" name="free_writer" 
					      id="free_writer" maxlength="50">
				</li>
				<li>
					<label for="free_content">내용</label>
					<textarea rows="5" cols="30" name="free_content"
					     id="free_content"></textarea>
				</li>
				<li>
					<label for="free_img">파일</label>
					<input type="file" name="free_img" 
					 id="free_img" 
					 accept="image/gif,image/png,image/jpeg">
				</li>
			<li>
					<label for="free_code">게시판 선택</label>
					<br/>
					<select name="free_code" id="free_code">
					      <option value="1">자유게시판</option>
					      <option value="2">음악추천받아요</option>
					</select>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="목록" 
				             onclick="location.href='freeBoard.do'">
			</div>
		</form>
	</div>
</div>
</body>
</html>

</body>
</html>