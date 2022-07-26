$(function(){
	//글 삭제 버튼
	$('#inquiryDelete_btn').click(function(){
		let choice = confirm('정말 삭제하시겠습니까');//확인:true, 취소:false
		let inq_num = $('#inq_num').val();
		if(choice){
			location.replace('deleteInqBoard.do?inq_num='+inq_num);
		}
	});
});