$(function(){
	//좋아요 선택 여부와 선택한 총 개수 읽기
	function selectData(the_num){
		$.ajax({
			url:'themeGetFav.do',
			type:'post',
			data:{the_num:the_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				displayFav(param);
			},
			error:function(){
				alert('1네트워크 오류');
			}
		});
	}
	
	//좋아요 등록
	$('#output_fav').click(function(){
		$.ajax({
			url:'themeWriteFav.do',
			type:'post',
			data:{the_num:$('#the_num').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result=='logout'){
					alert('로그인 후 좋아요를 놀러주세요!');
				}else if(param.result == 'success'){
					displayFav(param);
				}else{
					alert('등록시 오류 발생!');
				}
			},
			error:function(){
				alert('2네트워크 오류!');
			}
		});
	});
	//좋아요 표시
	function displayFav(param){
		let output;
		if(param.status == 'noFav'){
			output = '../images/fav01.gif';
		}else{
			output = '../images/fav02.gif';
		}
		//문서 객체에 추가
		$('#output_fav').attr('src',output);
		$('#output_fcount').text(param.count);
	}
	
	//초기 데이터 표시
	selectData($('#the_num').val());
	
});