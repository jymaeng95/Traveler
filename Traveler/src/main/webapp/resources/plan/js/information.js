/**
 * 
 */
$(document).ready(function(){
	var contentId = $("#contentId").val();
	var contentTypeId = $("#contentTypeId").val();
	var info_data=[];

	var allData = {"contentId" : contentId , "contentTypeId" : contentTypeId };
//	info_data=firstAjax(allData,contentTypeId);
	firstAjax(allData,contentTypeId);
	
});

function insertData(data,contentTypeId) {
	for(var i=0;i<data.length;i++){
		if(data[i] == null){
			data[i] = "";
		}
	}		
	$("#main-img").attr("src",data[0]);
	$(".title").text(data[1]);
	$("#overview").html(data[2]);
	$(".address").text(" "+data[3]);
	switch(contentTypeId) {
	case "12" :
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-info-circle'></i> 문의 및 안내 : "+data[6]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-calendar-alt'></i> 쉬는날 : "+data[7]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-clock'></i> 이용시간 : "+data[8]+"</p>");
		break;
	case "14" :
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-info-circle'></i>문의 및 안내 : "+data[6]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-won-sign'></i> 이용요금 : "+data[7]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-clock'> 이용시간 : "+data[8]+"</p>");
		break;
	case "15" :
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-info-circle'></i>행사 홈페이지 : "+data[6]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-phone-alt'></i> 연락처 : "+data[7]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-clock'> 공연시간 : "+data[8]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-won-sign'></i>이용요금 : "+data[9]+"</p>");//여기까지
		break;
	case "25" :
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-info-circle'></i>문의 및 안내 : "+data[6]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-clock'> 코스 예상 소요시간 : "+data[7]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-question-circle'></i> 코스 테마 : "+data[8]+"</p>");
		break;
	case "28" :
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-info-circle'></i> 문의 및 안내 : "+data[6]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-calendar-alt'></i> 쉬는날 : "+data[7]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-won-sign'></i> 이용요금 : "+data[8]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-clock'> 이용시간 : "+data[9]+"</p>");
		break;
	case "32" :
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-info-circle'></i> 문의 및 안내 : "+data[8]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-clock'> 체크인 : "+data[6]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-clock'> 체크아웃 : "+data[7]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-link'></i> 홈페이지 : "+data[9]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-question-circle'></i> 예약안내 : "+data[10]+"</p>");
		break;
	case "38" :
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-info-circle'></i> 문의 및 안내 : "+data[6]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-clock'> 영업시간 : "+data[7]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-calendar-alt'></i>쉬는날 : "+data[8]+"</p>");
		break;
	case "39" :
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-info-circle'></i> 문의 및 안내 : "+data[7]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='fas fa-1x fa-utensils'></i> 대표 메뉴 : "+data[6]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-clock'> 영업시간 : "+data[8]+"</p>");
		$(".details").append("<p style='font-weight:bold;'><i class='far fa-1x fa-calendar-alt'></i> 쉬는날 : "+data[9]+"</p>");
		break;
	}
}
function firstAjax(allData,contentTypeId) {
//	var result =[];
	$.ajax({
		url : "/spot/details",
		type : "get",
		dataType : "json",
//		async : false,
		data : allData,
		success : function(data) {
			insertData(data,contentTypeId);

		},
		error : function(error) {
			alert("데이터 에러");
		}
	});
//	return result;
}