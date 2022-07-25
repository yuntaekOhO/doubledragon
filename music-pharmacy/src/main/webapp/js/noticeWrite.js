$(function(){
	//=================게시판 글쓰기==================//
	$('#write_form').submit(function(){
		if($('#notice_title').val().trim()==''){
			alert('제목을 입력하세요!');
			$('#notice_title').val('').focus();
			return false;
		}		
		if($('#notice_content').val().trim()==''){
			alert('내용을 입력하세요!');
			$('#notice_content').val('').focus();
			return false;
		}		
	});
	
	//=================게시판 글쓰기==================//
	$('#search_form').submit(function(){
		if($('#keyword').val().trim()==''){
			alert('검색어를 입력하세요');
			$('#keyword').val('').focus();
			return false;
		}
	});
});