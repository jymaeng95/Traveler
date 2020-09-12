$(document).ready(function(){
	var contentId = $("#contentId").val();
	var contentTypeId = $("#contentTypeId").val();
	var userId = $("#userId").val();
	var info_data=[];

	var allData = {"contentId" : contentId , "contentTypeId" : contentTypeId };
	info_data=firstAjax(allData,contentTypeId);
	loadImage(contentId,contentTypeId);
	$("#bookmark").click(function(){
		if(userId==""){
			alert("로그인 후 이용해주세요.")
		} else{
			if($("#iconMark").attr("class")=="fas fa-star") {
				if(deleteBookmark(userId,contentId)){
					$("#iconMark").attr("class","far fa-star");
					alert("북마크 삭제 완료!");
				}else alert("북마크 삭제 실패!");
			}else {
				if(addBookmark(userId,contentId,contentTypeId,info_data)) {
					alert("북마크 등록 완료!")
					$("#iconMark").attr("class","fas fa-star");
				}
				else alert("북마크 등록 실패!");
			}
		}
	});
});
function firstAjax(allData,contentTypeId) {
	var result =[];
	$.ajax({
		url : "/spot/details",
		type : "get",
		dataType : "json",
		async : false,
		data : allData,
		success : function(data) {
			testData(data,contentTypeId);
			isBookmark(data);

			for(var i=0;i<6;i++){
				result.push(data[i]);
			}
		},
		error : function(error) {
			alert("데이터 에러");
		}
	});
	return result;
}
function testData(data,contentTypeId) {
	for(var i=0;i<data.length;i++){
		if(data[i] == null){
			data[i] = "";
		}
	}		
	$("#spot_img").attr("src",data[0]);
	$("#title").text(data[1]);
	$("#overview").html(data[2]);
	$(".details").append("<li id='addr'>주소 : "+data[3]+"</li><br>");
	switch(contentTypeId) {
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
	}
}
function isBookmark() {
	var result="";
	$.ajax({
		url : "/spot/check/bookmark",
		type : "get",
		dataType : "json",

		data : {
			"userId" : $("#userId").val(),
			"contentId" : $("#contentId").val() 
		},
		success : function(data) {
			if(data){
				$("#iconMark").attr("class","fas fa-star");
				result = data;
			}else {
				$("#iconMark").attr("class","far fa-star");
				result = data;
			}
		},
		error : function(error) {
			alert("데이터 에러");
		}
	});

}
function addBookmark(userId,contentId,contentTypeId,info_data){
	var result = "";
	$.ajax({
		url : "/spot/add/bookmark",
		type : "post",
		async :  false,
		dataType : "json",
		data : {
			"userId" : userId,
			"contentId" : contentId,
			"contentTypeId" : contentTypeId,
			"title" : info_data[1],
			"addr" : info_data[3],
			"img_src" : info_data[0],
			"overview" : info_data[2],
			"mapX" : info_data[4],
			"mapY" : info_data[5]
		},
		success : function(data) {
			result = data;
		},
		error : function(error) {
			alert("addBookmark 에러");
		}
	});
	return result;
}
function deleteBookmark(userId,contentId) {
	var result = "";
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
			result = data;
		},
		error : function(error) {
			alert("deleteBookmark 에러");
		}
	});
	return result;
}

function loadImage(contentId, contentTypeId) {
	$.ajax({
		url : "/spot/subimages",
		type : "GET",
		dataType : "json",
		data : {
			"contentId" : contentId,
			"contentTypeId" : contentTypeId,
			"numOfRow" : "4"
		},
		success : function(data) {
			for(var i=0;i<4;i++){
				$("#img-sub"+i).attr("src",data[i]);
			}
		}	
	});
}