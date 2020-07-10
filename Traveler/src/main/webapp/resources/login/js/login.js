$(document).ready(function(){
			$(".veen .rgstr-btn button").click(function(){
				$('.veen .wrapper').addClass('move');
				$('body').css('background','#7E998D');
				$(".veen .wrapper #login").css('padding-top','0%');
				$(".veen .login-btn button").removeClass('active');
				$(this).addClass('active');
				
			});
			$(".veen .login-btn button").click(function(){
				$('.veen .wrapper').removeClass('move');
				$('body').css('background','#96C4D8');
				$(".veen .wrapper #login").css('padding-top','20%');
				$(".veen .rgstr-btn button").removeClass('active');
				$(this).addClass('active');
			});
			$("#reg-btn").click(function(){
				var check = registerCheck();
				if(check) register();
			});
			$("#login-btn").click(function(){
				var check = loginCheck();
				if(check) login();
			});
			
		});

function login() {
	$.ajax({
		url : "/login/login",
		type : "post",
		dataType : "json",
		data : {
			"userId" : $("#uid_l").val(),
			"userPw" : $("#upw_l").val()
		},
		success : function(data) {
			if (data) {
				location.href="/";
			} 
			else{ 
				alert("회원 정보가 일치하지 않습니다.")
			}
		}
	});
}

//로그인 폼 유효성 체크 
function registerCheck() {
	form = document.regForm;
	if (form.uid.value == "" || form.upw.value == ""
			|| form.email.value == ""
			|| form.nickname.value == "") {
		if (form.uid.value == "") {
			alert("아이디를 입력하세요.");
			form.uid.focus();
			return false;
		} else if (form.upw.value  == "") {
			alert("패스워드를 입력하세요.");
			form.upw.focus();
			return false;
		} else if (form.email.value == "") {
			alert("이메일을 입력하세요.");
			form.email.focus();
			return false;
		} else {
			alert("닉네임을 입력하세요.");
			form.nickname.focus();
			return false;
		}
	} else {
		return true;
	}
}

//회원가입 
function register() {
	$.ajax({
		url : "/login/register",
		type : "post",
		dataType : "text",
		data : {
			"userId" : $("#uid_r").val(),
			"userPw" : $("#upw_r").val(),
			"email" : $("#email").val(),
			"nickname" : $("#nickname").val()
		},
		success : function(data) {
			alert(data);
			if(data === "회원가입에 성공하였습니다."){				
				location.reload();
			}
		},
		error : function(error) {
			alert("전송 실패");
		}
	});
}
//로그인 폼의 유효성 검사 
function loginCheck() {
	form = document.loginForm;
	if (form.uid.value == "" || form.upw.value == "") {
		if (form.uid.value == "") {
			alert("아이디를 입력하세요.");
			form.uid.focus();
			return false;
		} else {
			alert("패스워드를 입력하세요.");
			form.upw.focus();
			return false;
		}
	} else {
		return true;
	}
}