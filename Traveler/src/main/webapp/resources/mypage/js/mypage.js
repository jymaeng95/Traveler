$(document).ready(function(){
	$("#more-bookmark").click(function(){
		location.href="/mypage/bookmark";
	});
	$("#delete-user").click(function(){
		alert("탈퇴하시겠습니까?");
		$("#myModal").modal("show");

	});
	$("#btn-modify").click(function(){
		location.href="/mypage/modify";
	});
	$("#btn-photo").click(function(){
		$('input[type=file]').click();
	});
	$("#confirm").click(function(){
		if($("#upw").val()=="") {
			alert("패스워드를 입력하세요.")
			$("#upw").focus();
		} else {
			$.ajax({
				url : "/mypage/delete",
				type : "post",
				dataType : "json",
				data : {
					"userId" : $("#session-id").val(),
					"userPw" : $("#upw").val()
				},
				success : function(data){
					if(data){
						$("#myModal").modal("hide");
						location.href= "/";
					}
					else {
						$("#myModal").modal("hide");
						alert("패스워드가 일치하지않습니다.")
					}
				},
				error : function(error) {
					alert("에러")
				}
			});
		}
	});
});

