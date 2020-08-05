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
	$(".address").text(data[3]);
	/*switch(contentTypeId) {
	case "12" :
		$(".details").append("<li>문의 및 안내 : "+data[6]+"</li><br>");
		$(".details").append("<li>쉬는날 : "+data[7]+"</li><br>");
		$(".details").append("<li>이용시간 : "+data[8]+"</li><br>");
		break;
	case "14" :
		$(".details").append("<li>문의 및 안내 : "+data[6]+"</li><br>");
		$(".details").append("<li>이용요금 : "+data[7]+"</li><br>");
		$(".details").append("<li>이용시간 : "+data[8]+"</li><br>");
		break;
	case "15" :
		$(".details").append("<li>행사 홈페이지 : "+data[6]+"</li><br>");
		$(".details").append("<li>연락처 : "+data[7]+"</li><br>");
		$(".details").append("<li>공연시간 : "+data[8]+"</li><br>");
		$(".details").append("<li>이용요금 : "+data[9]+"</li><br>");//여기까지
		break;
	case "25" :
		$(".details").append("<li>문의 및 안내 : "+data[6]+"</li><br>");
		$(".details").append("<li>코스 예상 소요시간 : "+data[7]+"</li><br>");
		$(".details").append("<li>코스 테마 : "+data[8]+"</li><br>");
		break;
	case "28" :
		$(".details").append("<li>문의 및 안내 : "+data[6]+"</li><br>");
		$(".details").append("<li>쉬는날 : "+data[7]+"</li><br>");
		$(".details").append("<li>이용요금 : "+data[8]+"</li><br>");
		$(".details").append("<li>이용시간 : "+data[9]+"</li><br>");
		break;
	case "32" :
		$(".details").append("<li>문의 및 안내 : "+data[8]+"</li><br>");
		$(".details").append("<li>체크인 : "+data[6]+"</li><br>");
		$(".details").append("<li>체크아웃 : "+data[7]+"</li><br>");
		$(".details").append("<li>홈페이지 : "+data[9]+"</li><br>");
		$(".details").append("<li>예약안내 : "+data[10]+"</li><br>");
		break;
	case "38" :
		$(".details").append("<li>문의 및 안내 : "+data[6]+"</li><br>");
		$(".details").append("<li>영업시간 : "+data[7]+"</li><br>");
		$(".details").append("<li>쉬는날 : "+data[8]+"</li><br>");
		break;
	case "39" :
		$(".details").append("<li>문의 및 안내 : "+data[7]+"</li><br>");
		$(".details").append("<li>대표 메뉴 : "+data[6]+"</li><br>");
		$(".details").append("<li>영업시간 : "+data[8]+"</li><br>");
		$(".details").append("<li>쉬는날 : "+data[9]+"</li><br>");
		break;
	}*/
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

			for(var i=0;i<6;i++){
				result.push(data[i]);
			}
		},
		error : function(error) {
			alert("데이터 에러");
		}
	});
//	return result;
}