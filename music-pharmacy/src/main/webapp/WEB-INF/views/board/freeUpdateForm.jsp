<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>저잣거리 글수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.fav.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
</div>
<div class="page">
 	<a href="${pageContext.request.contextPath}/board/freeBoard.do">저잣거리</a>
 	<p>자유게시판/음악추천받아요</p><br>
	<div class="content-main">
		<h4>글수정</h4>
		<form action="freeUpdate.do" method="post"
		      enctype="multipart/form-data" id="write_form">
			<input type="hidden" name="free_num" 
			                       value="${board.free_num}">
	     	<ul>
				<li>
					<label for="free_code"></label>
					<select name="free_code" id="free_code">
					      <option value="1">자유게시판</option>
					      <option value="2">음악추천받아요</option>
					</select>
					<label for="free_title"></label>
					<input type="text" name="free_title" 
					      id="free_title" value=" ${board.free_title}" maxlength="100">
				</li>
				<br>
				<li>
					<label for="free_content"></label>
					<textarea rows="30" cols="185" name="free_content"
					     id="free_content">${board.free_content}</textarea>
				</li>
				<li>
					<label for="free_img"></label>
					<input type="file" name="free_img" 
					 id="free_img" 
					 accept="image/gif,image/png,image/jpeg">
					<c:if test="${!empty board.free_img}">
					<br>
					<span id="file_detail">
						(${board.free_img})파일이 등록되어 있습니다. 
						다시 파일을 업로드하면 기존 파일은 삭제됩니다.
						<input type="button" value="파일삭제" id="file_del">
					</span>
					<script type="text/javascript">
					$(function(){
						//이벤트 연결
						$('#file_del').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									type:'post',
									data:{free_num:${board.free_num}},
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요!');
										}else if(param.result == 'success'){
											$('#file_detail').hide();
										}else if(param.result == 'wrongAccess'){
											alert('잘못된 접속입니다.');
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
					</c:if>  
				</li>
			</ul> 
			<div class="align-right">
				<input type="submit" value="수정">
				<input type="button" value="목록"
				         onclick="location.href='freeBoard.do'">
			</div>                      
		</form>
	</div>
</div>
</body>
</html>




