$(document).ready(function(){
	$(".btn-acc").on("click",function(){
		var title = $(this).siblings("h1.title").text();
		console.log(title);
		var planNo = $(this).val();
		console.log(planNo);
		
		var form = document.createElement("form");
		form.setAttribute("method", "GET");  //Post 방식
		form.setAttribute("action", "/accompany/register/detail"); //요청 보낼 주소
		form.setAttribute("target","detail");

		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "planNo");
		hiddenField.setAttribute("value", planNo);
		form.appendChild(hiddenField);
		
		var titleField = document.createElement("input");
		titleField.setAttribute("type", "hidden");
		titleField.setAttribute("name", "title");
		titleField.setAttribute("value", title);
		form.appendChild(titleField);
		
		document.body.appendChild(form);
		form.submit();
		$("#modal-register").modal();
	});
});