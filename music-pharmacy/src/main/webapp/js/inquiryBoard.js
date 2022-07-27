$(function(){
	//글 삭제 버튼
	$('#inquiryDelete_btn').click(function(){
		let choice = confirm('정말 삭제하시겠습니까');//확인:true, 취소:false
		let inq_num = $('#inq_num').val();
		if(choice){
			location.replace('deleteInqBoard.do?inq_num='+inq_num);
		}
	});
	
	//글 목록 검색 버튼
	$('#inq_search_form').submit(function(){
		if($('#keyword').val().trim()==''){
			alert('검색어를 입력해주세요');
			$('#search').val('').focus();
			return false;
		}
	});
	
	//글작성
	$('#inq_write_form').submit(function(){
		if($('#inq_title').val().trim()==''){
			alert('제목을 입력해주세요');
			$('#inq_title').val('').focus();
			return false;
		}
		if($('#inq_question').val().trim()==''){
			alert('질문을 입력해주세요');
			$('#inq_question').val('').focus();
			return false;
		}
		if($('#inq_answer').val().trim()==''){
			alert('답변을 입력해주세요');
			$('#inq_answer').val('').focus();
			return false;
		}
		
	});
});