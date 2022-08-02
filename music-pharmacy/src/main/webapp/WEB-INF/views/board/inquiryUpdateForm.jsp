<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상소문수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style3.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inquiryBoard.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
<div>
	<div class="page">
 	<a href="${pageContext.request.contextPath}/board/inquiryBoard.do">상소문</a>
 	<p>자주 묻는 질문</p>
	<form id="inq_write_form"  action="updateInq.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="inq_num" value="${board.inq_num}">
		<ul>
			<li>
				<label for="inq_question">질문</label>
				<textarea id="inq_question" name="inq_question" rows="2" cols="50">${board.inq_question}</textarea>
			</li>
			<li>
				<label for="inq_img">파일</label>
				<input type="file" name="inq_img" id="inq_img" accept="image/gif,image/png,image/jpeg">
				<c:if test="${!empty board.inq_img}">
					<br>
					<span id="file_detail">
						(${board.inq_img})파일이 등록되어 있습니다.
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
									url:'deleteInqFile.do',
									type:'post',
									data:{inq_num:${board.inq_num}},
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
				</c:if>
			</li>
			<li>
				<label for="inq_answer">답변</label>
				<textarea id="inq_answer" name="inq_answer" rows="5" cols="50">${board.inq_answer}</textarea>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" class="correction_btn" value="수정">
			<input type="button" class="correction_btn" value="목록" onclick="location.href='${pageContext.request.contextPath}/board/inquiryBoard.do'">
		</div>
	</form>
</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>