//Taken from https://codepen.io/jonnitto/project/editor/XRPjxx

//Minimal Javascript (for Edge, IE and select box)
document.addEventListener("change", function(event) {
	let element = event.target;
	if (element && element.matches(".form-element-field")) {
		element.classList[element.value ? "add" : "remove"]("-hasvalue");
	}
});
$(document).ready(function() {
	if($("#iskakao").val() == "Y"){
		$("#upw").prop("readonly",true);
		$("#currentpw").prop("readonly",true);
	}
	$("#btn-cancel").click(function(){
		window.history.back();
	});
	$("#btn-submit").click(function(){
		var frmCheck;
		var n_check = false;
		var cur_nick = $("#cur-nickname").val();
		var change_nick = $("#nickname").val();
		if($("#iskakao").val() == "Y"){
			frmCheck = kakaoFormCheck();
			if(frmCheck) {
				if(cur_nick != change_nick) n_check = nickCheck(); 
				if(!n_check){
					$("#infoForm").attr("action","/mypage/modify");
					$("#infoForm").submit();
				}
			}
		} else {
			frmCheck = formCheck();
			if(frmCheck) {
				var p_check = pwCheck();
				var cur_pw = $("#currentpw").val();
				var change_pw = $("#upw").val();

				if(cur_nick != change_nick) n_check = nickCheck(); 
				if(p_check&&!n_check) {


					$("#infoForm").attr("action","/mypage/modify");
					$("#infoForm").submit();

				}
			}
		}
	});
});

//폼(일반)의 유효성 검사 
function formCheck() {
	form = document.infoForm;
	if (form.upw.value == "" || form.email.value == ""
		|| form.currentpw.value == "" || form.nickname.value == "") {
		if (form.currentpw.value  == "") {
			alert("현재 패스워드를 입력하세요.");
			form.currentpw.focus();
			return false;
		} else if (form.upw.value == "") {
			alert("새로운 패스워드를 입력하세요.");
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

//폼(카카오) 유효성 검사 
function kakaoFormCheck() {
	form = document.infoForm;
	if (form.email.value == "" || form.nickname.value == "") {
		if (form.email.value == "") {
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

function pwCheck() {
	var result;
	$.ajax({
		url : "/mypage/pwdcheck",
		type : "post",
		async : false,
		dataType : "json",
		data : {
			"userId" : $("#uid").val(),
			"userPw" : $("#currentpw").val()
		},
		success : function(data) {
			if(data) result = data;
			else {
				alert("패스워드가 틀립니다.");
				$("#currentpw").val("");
			}
		},
		error : function(error) {
			alert("전송 오류");
		}
	});
	return result;
}
//닉네임 중복 확인
function nickCheck() {
	var result;
	$.ajax({
		url : "/login/nickCheck",
		type : "post",
		async :false,
		dataType : "json",
		data : {
			"nickname" : $("#nickname").val()
		},
		success : function(data) {
			result=data;
			if (data) {
				alert("중복된 닉네임 입니다.");
				$("#nickname").val("");
			} else {
				alert("사용 가능한 닉네임 입니다.");
			}
		}

	});
	return result;
}