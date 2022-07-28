$(function(){
	//글 삭제 버튼
	$('#noticeDelete_btn').click(function(){
		let choice = confirm('정말 삭제하시겠습니까');//확인:true, 취소:false
		let not_num = $('#not_num').val();
		if(choice){
			location.replace('noticeDelete.do?not_num='+not_num);
		}
	});
	
	//글 목록 검색 버튼
	$('#not_search_form').submit(function(){
		if($('#keyword').val().trim()==''){
			alert('검색어를 입력해주세요');
			$('#search').val('').focus();
			return false;
		}
	});
	
	//글작성
	$('#not_write_form').submit(function(){
		/*if($('#inq_title').val().trim()==''){
			alert('제목을 입력해주세요');
			$('#inq_title').val('').focus();
			return false;
		}*/
		if($('#not_title').val().trim()==''){
			alert('제목을 입력해주세요');
			$('#not_title').val('').focus();
			return false;
		}
		if($('#not_content').val().trim()==''){
			alert('내용을 입력해주세요');
			$('#not_content').val('').focus();
			return false;
		}
	});
	
	
});