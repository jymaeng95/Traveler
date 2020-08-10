$(document).ready(function(){
	$("#new_plan").click(function(){
		$("#myModal").modal("show");

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

			//			$.ajax({
			//			url : "/plan/create_info",
			//			type : "post",
			//			dataType : "json",
			//			data : {
			//			"p_title" : $("#p_title").val(),
			//			"p_date" : $("#p_date").val()
			//			},
			//			success : function(data){
			//			if(data){
			//			$("#myModal").modal("hide");
			//			location.href= "/plan/create";
			//			}
			//			},
			//			error : function(error) {
			//			alert("에러")
			//			}
			//			});
		}
	});

	$("#p_date").flatpickr({
		mode: "range",
		inline: true,
		static : true
	});

});
