<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/noticeDetail.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/noticeList.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiho/footer.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/noticeHeader.jsp"/>
<div class="page-main"><br><br><br><br>
	<div class="content-main">
		<ul class="detail-info">
			<li>
				<h4>${board.not_title}</h4><br>
				<c:if test="${!empty member.photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.photo}" width="40" height="40" class="my-photo">
				</c:if>
				<c:if test="${empty member.photo}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
				</c:if>
				${board.id}님
			</li> 
		</ul>
		<c:if test="${!empty board.not_img}">
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/upload/${board.not_img}" class="detail-img">
		</div>
		</c:if>
		<p>
			${board.not_content}
		</p>
		<hr size="1" noshade="noshade" width="100%">

		<ul class="detail-sub">
			<li>
				<c:if test="${!empty board.not_modify_date}">
				최근 수정일 : ${board.not_modify_date}
				</c:if>
				작성일 : ${board.not_date}
				<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정,삭제 가능 --%>
				<c:if test="${user_num == board.mem_num}"> <!-- 관리자만 글 쓸수있으니깐 관리자만 수정삭제버튼 보여요 -->
				<input type="button" value="수정" 
				 onclick="location.href='noticeUpdateForm.do?not_num=${board.not_num}'">
				<input type="button" value="삭제" id="delete_btn">
				<script type="text/javascript">
					let delete_btn = document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick=function(){
						let choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('noticeDelete.do?not_num=${board.not_num}');
						}
					};
				</script>
				</c:if>
			</li>
			
	
	
		</ul>
		
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>





