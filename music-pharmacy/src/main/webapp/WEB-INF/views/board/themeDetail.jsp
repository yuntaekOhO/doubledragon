<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/themeboard.fav.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>
	<div class="content-main">
		<h2>${board.the_title}</h2>
		<ul class="detail-info">
			<li>
				
			</li>
			<li>

				조회 : ${board.the_hits}
			</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty board.the_img}">
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/upload/${board.the_img}" class="detail-img">
		</div>
		</c:if>
		
		
		<p>
			${board.the_content}
			${board.the_url}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<%-- 좋아요 --%>
				<input type="hidden" name="the_num" value="${board.the_num}" id="the_num">
				<img id="output_fav" src="${pageContext.request.contextPath}/images/fav01.gif" width="50">
				좋아요
				<span id="output_fcount"></span>
			</li>
			<li>
				<c:if test="${!empty board.the_modify_date}">
				최근 수정일 : ${board.the_modify_date}
				</c:if>
				작성일 : ${board.the_date}
				<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정,삭제 가능 --%>
				<c:if test="${user_num == board.mem_num}">
				<input type="button" value="수정" 
				 onclick="location.href='themeUpdateForm.do?the_num=${board.the_num}'">
				<input type="button" value="삭제" id="delete_btn">
				<script type="text/javascript">
					let delete_btn = document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick=function(){
						let choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('themeDelete.do?the_num=${board.the_num}');
						}
					};
				</script>
				</c:if>
			</li>
		</ul>
		
	</div>
</div>
</body>
</html>





