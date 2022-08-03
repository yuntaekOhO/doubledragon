<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동의보감 글수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>

<div class="page">
	<a href="${pageContext.request.contextPath}/board/themeBoard.do">동의보감</a>
 	<p>음악추천게시판</p>
 		<div class="content-main">
		<h4>글수정</h4>
		<form id="write_form" action="themeUpdate.do" 
		   method="post" enctype="multipart/form-data">
		   <input type="hidden" name="the_num" value="${board.the_num}">
			<ul>
				<li>
					<label for="the_code"></label>
					<select name="the_code" id="the_code">
					      <option value="1">희喜</option>
					      <option value="2">노怒</option>
					      <option value="3">애哀</option>
					      <option value="4">락樂</option>
					</select>
					<label for="the title"></label>
					<input type="text" name="the_title" id="the_title" value="${board.the_title}" maxlength="50">
				</li>

				
				<li>
					<label for="mus_img">앨범 이미지</label>
					<input type="file" name="mus_img" 
					 id="mus_img" 
					 accept="image/gif,image/png,image/jpeg,image/jpg">
					<br>
					<span id="file_detail">
						(${music.mus_img})파일이 등록되어 있습니다.
						다시 파일을 업로드하면 기존 파일은 삭제됩니다.
						<input type="button" class="correction_btn" value="파일삭제" id="file_del"><%--ajax--%>
					</span>
					<script type="text/javascript">
					$(function(){
						//이벤트 연결
						$('#file_del').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'themeDeleteFile.do',
									type:'post',
									data:{the_num:${board.the_num}},
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result=='logout'){
											alert('로그인 후 사용하세요');
										}else if(param.result=='success'){
											$('#file_detail').hide();
										}else if(param.result=='wrongAccess'){
											alert('잘못된 접속입니다');
										}else{
											alert('파일 삭제 오류 발생');
										}
									},
									error:function(){
										alert('네트워크 오류 발생');
									}
								});
							}
						});
					});
					</script>
					
				</li>
				<li>
					<label for="mus_title">곡 제목</label>
					<input type="text" name="mus_title" id="mus_title" value="${music.mus_title}">
				</li>
				<li>
					<label for="mus_singer">가수</label>
					<input type="text" name="mus_singer" id="mus_singer" value="${music.mus_singer}">
				</li>
				<li>
					<label for="mus_album">앨범명</label>
					<input type="text" name="mus_album" id="mus_album" value="${music.mus_album}">
				</li>
				<li>
					<label for="mus_genre">장르</label>
					<select name="mus_genre" id="mus_genre">
					
						<option value="발라드">발라드</option>
						<option value="pop">pop</option>
						<option value="댄스">댄스</option>
						<option value="힙합">힙합</option>
						<option value="인디">인디</option>
						<option value="etc">etc</option>
					</select>
				</li>
				<li>
					<label for="mus_date">발매일</label>
					<input type="date" name="mus_date" id="mus_date">
				</li>
				<li>
					<label for="mus_composer">작곡가</label>
					<input type="text" name="mus_composer" id="mus_composer" value="${music.mus_composer}">
				</li>
				<li>
					<label for="mus_songwriter">작사가</label>
					<input type="text" name="mus_songwriter" id="mus_songwriter" value="${music.mus_songwriter}">
				</li>
					<li>
					<label for="the_url">영상 링크</label>
					<input type="url" name="the_url" id="the_url" value="${board.the_url}">
				</li>
				<br>
				<li>
					<label for="the_content"></label>
					<textarea rows="30" cols="185" name="the_content"
					     id="the_content">${board.the_content}</textarea>
				</li>
				<!--
				<li>
					<label for="the_img"></label>
					<input type="file" name="the_img" id="the_img" accept="image/gif,image/png,image/jpeg" value="${board.the_img}">
				</li>
			 	<li>
					<label for="the_video">관련 영상</label>
					<input type="file" name="the_video" 
					 id="the_video" 
					 accept="video/mp4,video/avi,video/wmv">
				</li> -->
				
			</ul>
			<div class="align-right">
				<input type="submit" class="correction_btn" value="수정">
				<input type="button" class="correction_btn" value="목록" 
				             onclick="location.href='themeBoard.do'">
			</div>
		</form>

	</div>
</div>
<div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>





