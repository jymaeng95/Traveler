
function readMoreClick(contentId,contentTypeId){
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

function deleteBookmark(userId,contentId) {
	alert("북마크를 제거하시겠습니까?");
	$.ajax({
		url : "/spot/delete/bookmark",
		type : "post",
		async : false,
		dataType : "json",
		data : {
			"userId" : userId,
			"contentId" : contentId				
		},
		success : function(data) {
			alert("북마크 삭제 완료!");
			location.reload();
		},
		error : function(error) {
			alert("deleteBookmark 에러");
		}
	});
}