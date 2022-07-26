$(function(){
	let idChecked = 0;
	
	//============= 회원가입 ==============//
	//아이디 중복 체크 이벤트 연결
	$('#id_check').click(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#id').val('').focus();
			return;
		}
		
		//아이디 중복 여부를 표시하는 메시지 초기화
		$('#message_id').text('');
		
		//서버와 데이터 통신
		$.ajax({
			url:'checkDuplicatedId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'idNotFound'){
					idChecked = 1;
					$('#message_id').css('color','#000000').text('등록 가능 ID');
				}else if(param.result == 'idDuplicated'){
					idChecked = 0;
					$('#message_id').css('color','red').text('중복된 ID');
					$('#id').val('').focus();
				}else{
					idChecked = 0;
					alert('아이디 중복 체크 오류 발생');
				}
			},
			error:function(){
				idChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
	});//end of click
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #id').keydown(function(){
		idChecked = 0;
		$('#message_id').text('');
	});
	
	//회원 정보 등록 유효성 체크
	$('#register_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#id').val('').focus();
			return false;
		}
		if(idChecked==0){
			alert('아이디 중복 체크 필수');
			return false;
		}
		if($('#name').val().trim()==''){
			alert('이름을 입력하세요!');
			$('#name').val('').focus();
			return false;
		}
		if($('#nick').val().trim()==''){
			alert('닉네임을 입력하세요!');
			$('#nick').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#cell').val().trim()==''){
			alert('전화번호를 입력하세요!');
			$('#cell').val('').focus();
			return false;
		}
		if($('#email').val().trim()==''){
			alert('이메일을 입력하세요!');
			$('#email').val('').focus();
			return false;
		}
		if($('#zipcode').val().trim()==''){
			alert('우편번호를 입력하세요!');
			$('#zipcode').val('').focus();
			return false;
		}
		if($('#addr1').val().trim()==''){
			alert('주소를 입력하세요!');
			$('#addr1').val('').focus();
			return false;
		}
		if($('#addr2').val().trim()==''){
			alert('나머지 주소를 입력하세요!');
			$('#addr2').val('').focus();
			return false;
		}
		if($('#birthday').val().trim()==''){
			alert('생년월일을 입력하세요!');
			$('#birthday').val('').focus();
			return false;
		}
		if($('#route').val().trim()==''){
			alert('가입경로를 선택하세요!');
			$('#route').val('').focus();
			return false;
		}
		if($('#music').val().trim()==''){
			alert('장르를 선택하세요!');
			$('#music').val('').focus();
			return false;
		}
	});
	
	//로그인 이벤트 연결
	$('#login_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#id').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
	});
	
	//프로필 사진
	//프로필 사진 업데이트 이벤트 연결
	$('#photo_btn').click(function(){
		$('#photo_choice').show();
		$(this).hide();
	});
	
	let photo_path = $('.my-photo').attr('src');
	let my_photo;
	$('#photo').change(function(){
		my_photo = this.files[0];
		if(!my_photo){
			$('.my-photo').attr('src',photo_path);
			return;
		}
		
		if(my_photo.size > 1024*1024){
			alert(Math.round(my_photo.size/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$('.my-photo').attr('src',photo_path);
			$(this).val('');
			return;
		}
		
		var reader = new FileReader();
		reader.readAsDataURL(my_photo);
		
		reader.onload=function(){
			$('.my-photo').attr('src',reader.result);
		}
	});
	
	$('#photo_submit').click(function(){
		if($('#photo').val()==''){
			alert('파일을 선택하세요!');
			$('#photo').focus();
			return;
		}
		
		let form_data = new FormData();
		form_data.append('photo',my_photo);
		$.ajax({
			url:'updateMyPhoto.do',
			type:'post',
			data:form_data,
			dataType:'json',
			contentType:false,
			processData:false,
			enctype:'multipart/form-data',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'success'){
					alert('프로필 사진이 수정되었습니다.');
					photo_path = $('.my-photo').attr('src');
					$('#photo').val('');
					$('#photo_choice').hide();
					$('#photo_btn').show();
				}else{
					alert('파일 전송 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	$('#photo_reset').click(function(){
		$('.my-photo').attr('src',photo_path);
		$('#photo').val('');
		$('#photo_choice').hide();
		$('#photo_btn').show();
	});
	
	
	$('#modify_form').submit(function(){
		if($('#name').val().trim()==''){
			alert('이름을 입력하세요!');
			$('#name').val('').focus();
			return false;
		}
		if($('#nick').val().trim()==''){
			alert('닉네임을 입력하세요!');
			$('#nick').val('').focus();
			return false;
		}
		if($('#cell').val().trim()==''){
			alert('전화번호를 입력하세요!');
			$('#cell').val('').focus();
			return false;
		}
		if($('#email').val().trim()==''){
			alert('이메일을 입력하세요!');
			$('#email').val('').focus();
			return false;
		}
		if($('#zipcode').val().trim()==''){
			alert('우편번호를 입력하세요!');
			$('#zipcode').val('').focus();
			return false;
		}
		if($('#addr1').val().trim()==''){
			alert('주소를 입력하세요!');
			$('#addr1').val('').focus();
			return false;
		}
		if($('#addr2').val().trim()==''){
			alert('나머지 주소를 입력하세요!');
			$('#addr2').val('').focus();
			return false;
		}	
		if($('#birthday').val().trim()==''){
			alert('생일을 입력하세요!');
			$('#birthday').val('').focus();
			return false;
		}	
		
	});
	
	//============= 비밀번호수정 ==============//
	$('#password_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#id').val('').focus();
			return false;
		}
		if($('#origin_passwd').val().trim()==''){
			alert('현재 비밀번호를 입력하세요!');
			$('#origin_passwd').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('새비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#cpasswd').val().trim()==''){
			alert('새비밀번호 확인을 입력하세요!');
			$('#cpasswd').val('').focus();
			return false;
		}
		if($('#passwd').val()!=$('#cpasswd').val()){
			alert('새비밀번호와 새비밀번호 확인 불일치');
			$('#passwd').val('').focus();
			$('#cpasswd').val('');
			return false;
		}
	});
	
	
	//새비밀번호 확인까지 한 후 다시 새비밀번호를 수정하려고 하면
	//새비밀번호 확인을 초기화
	$('#passwd').keyup(function(){
		$('#cpasswd').val('');
		$('#message_cpasswd').text('');
	});
	
	//새비밀번호와 새비밀번호 확인 일치시 메시지 처리
	$('#cpasswd').keyup(function(){
		if($('#passwd').val()==$('#cpasswd').val()){
			$('#message_cpasswd').text('새비밀번호 일치');
		}else{
			$('#message_cpasswd').text('');
		}
	});
	
	//============= 회원탈퇴 ==============//
	$('#delete_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요!');
			$('#id').val('').focus();
			return false;
		}
		if($('#email').val().trim()==''){
			alert('이메일을 입력하세요!');
			$('#email').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요!');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#cpasswd').val().trim()==''){
			alert('비밀번호 확인을 입력하세요!');
			$('#cpasswd').val('').focus();
			return false;
		}
		if($('#passwd').val()!=$('#cpasswd').val()){
			alert('비밀번호와 비밀번호 확인 불일치');
			$('#passwd').val('').focus();
			$('#cpasswd').val('');
			return false;
		}
	});
	//비밀번호 확인까지 한 후 다시 비밀번호를 수정하면 비밀번호 확인 및
	//메시지 초기화
	$('#passwd').keyup(function(){
		$('#cpasswd').val('');
		$('#message_id').text('');
	});
	
	//비밀번호와 비밀번호 확인 일치 여부 체크
	$('#cpasswd').keyup(function(){
		if($('#passwd').val()==$('#cpasswd').val()){
			$('#message_id').text('비밀번호 일치');
		}else{
			$('#message_id').text('');
		}
	});
	
});