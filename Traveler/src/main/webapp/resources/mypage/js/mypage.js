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
	$('#file-upload').change(function(){
		  alert($('input[type=file]')[0].files[0].name); //파일이름
	       alert($("#file-upload")[0].files[0].type); // 파일 타임
	       alert($("#file-upload")[0].files[0].size); // 파일 크기
	       $("#upload-form").submit();
	});
	$("#confirm").click(function(){
		if($('#is_kakao').val() != 'Y') {
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
		} else {
			$.ajax({
				url : "/mypage/delete/kakao",
				type : "post",
				dataType : "json",
				data : {
					"userId" : $("#session-id").val(),
					"is_kakao" : $("#is_kakao").val()
				},
				success : function(data){
					if(data){
						$("#myModal").modal("hide");
						location.href= "/";
					}
					else {
						$("#myModal").modal("hide");
						alert("탈퇴에 실패했습니다.")
					}
				},
				error : function(error) {
					alert("에러")
				}
			});
		}
	});
});
function imgClick(contentId,contentTypeId){
	var form = document.createElement("form");
	form.setAttribute("charset", "UTF-8");
	form.setAttribute("method", "GET");  //Post 방식
	form.setAttribute("action", "/spot/detail"); //요청 보낼 주소

	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "contentId");
	hiddenField.setAttribute("value", contentId);
	form.appendChild(hiddenField);
	hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "contentTypeId");
	hiddenField.setAttribute("value", contentTypeId);
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
}

