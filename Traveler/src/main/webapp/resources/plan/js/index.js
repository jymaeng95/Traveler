$(document).ready(function(){
	$("#new_plan").click(function(){
		if($("#userId").val() == "") {
			alert("로그인하세요")
			location.href = "/";
		}else {
			$("#myModal").modal("show");
		}
	});

	$(".planner__button").on("click",function(){
		var planNo = $(this).val();
		var modifyForm = document.getElementById("modifyForm");
		var input = document.createElement('input');
		input.setAttribute("type","hidden");
		input.setAttribute("name","planNo");
		input.setAttribute("value",planNo);
		modifyForm.appendChild(input);
		modifyForm.submit();
	});
	$("#confirm").click(function(){
		if($("#p_title").val()=="" || $("#p_date").val()=="") {
			alert("내용을 모두 입력해주세요");
			$("#p_title").focus();
		} else {
			var strArr = $("#p_date").val();
			var stDate = new Date(parseInt(strArr.substr(0,4)), parseInt(strArr.substr(5,2))-1, parseInt(strArr.substr(8,2)));
			var btDay = 0; // 여행 일수

			if(strArr.length > 10) {
				var endDate = new Date(parseInt(strArr.substr(14,4)), parseInt(strArr.substr(19,2))-1, parseInt(strArr.substr(22,2)));
				var btMs = endDate.getTime() - stDate.getTime();
				btDay = btMs / (1000*60*60*24) + 1; // 두 날짜 차이 계산
			} else {
				btDay = 1;
			}

			alert(btDay)
			document.getElementById("t_date").value= btDay; 
			$("#plan_info").submit();
		}
	});

	$("#p_date").flatpickr({
		mode: "range",
		minDate: "today",
		inline: true,
		static : true
	});

	$('.planLoad').each(function(i){
		$(this).click(function(){
			var modifyForm = document.getElementById("modifyForm");
			var input = document.createElement('input');
			input.setAttribute("type","hidden");
			input.setAttribute("name","planNo");
			input.setAttribute("value",$(this).attr("id"));
			modifyForm.appendChild(input);
			modifyForm.submit();
		});
	});

});