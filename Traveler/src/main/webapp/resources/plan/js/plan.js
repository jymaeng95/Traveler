$(document).ready(function() {
	var sigungu = $("#sigunguCode").val();
	var contentType = $("#contentTypeId").val();
	var pNum = $('input[name=pageNum]').val()
	var contentInfo = {"pageNo" : pNum ,"sigunguCode" : sigungu, "contentTypeId" : contentType, "numOfRow" : "5" };
	var reco_positions=[];
	var bmk_positions=[];
	var map = initTmap();
	var ex_markers=[];
	$.ajax({
		url : "/spot/information",
		type : "get",
		dataType : "json",
		async : false,
		data : contentInfo,
		success : function(data) {
			$.each(data, function(key, val){
				//alert(val[key]);
				$("#r_photo"+key).attr("src",data[key].firstImage2);
				$("#r_title"+key).text(data[key].title);
				$("#r_mapX"+key).text(data[key].mapX);
				$("#r_mapY"+key).text(data[key].mapY);

				reco_positions.push({title : data[key].title, lonlat : new Tmapv2.LatLng(data[key].mapX, data[key].mapY)});
//				$("#addr"+key).text(data[key].addr1);
//				$("#overview"+key).html(data[key].overview);
//				$("#btn-detail"+key).attr("onclick","imgClick("+data[key].contentId+","+data[key].contentTypeId+");");
				$(".totalCount").text(data[key].totalCount);
			});					
		},
		error : function(error) {
			alert("첫페이지 에러");
		}
	});
//	initTmap(positions);
	
	$.ajax({
		url : "/plan/bookmark",
		type : "get",
		dataType : "json",
		async : false,
		data : { "userId" : $("#userId").val() },
		success : function(data) {
			$.each(data, function(key, val){
				//alert(val[key]);
				$("#b_photo"+key).attr("src",data[key].img_src);
				$("#b_title"+key).text(data[key].title);
				$("#b_mapX"+key).text(data[key].mapX);
				$("#b_mapY"+key).text(data[key].mapY);

				bmk_positions.push({title : data[key].title, lonlat : new Tmapv2.LatLng(data[key].mapX, data[key].mapY)});
//				$("#addr"+key).text(data[key].addr1);
//				$("#overview"+key).html(data[key].overview);
//				$("#btn-detail"+key).attr("onclick","imgClick("+data[key].contentId+","+data[key].contentTypeId+");");
				$(".totalCount").text(data[key].totalCount);
			});					
		},
		error : function(error) {
			alert("첫페이지 에러");
		}
	});
	
	$("#bookmark").click(function(){
		if(ex_markers != false) deleteMarkers(ex_markers);
		
		$("#ul-recommend").hide();
		$("#ul-bookmark").show();
		ex_markers = addMarkers(map,bmk_positions);
	});
	$("#recommend").click(function(){
		if(ex_markers != false) deleteMarkers(ex_markers);
		
		$("#ul-recommend").show();
		$("#ul-bookmark").hide();
		ex_markers = addMarkers(map,reco_positions);
	});
	$("#myplan").click(function(){
		if(ex_markers != false) deleteMarkers(ex_markers);
	});
});
function addMarkers(map,positions){
	var markers = [];
	for (var i = 0; i< positions.length; i++){//for문을 통하여 배열 안에 있는 값을 마커 생성
		var lonlat = positions[i].lonlat;
		var title = positions[i].title;
		alert(positions[i].title);
		//Marker 객체 생성.
		var marker = new Tmapv2.Marker({
			position : lonlat, //Marker의 중심좌표 설정.
			map : map, //Marker가 표시될 Map 설정.
			title : title //Marker 타이틀.
		});
		markers.push(marker);
	}
	return markers;
}
function deleteMarkers(markers){
	for (var i=0;i<markers.length; i++) {
		markers[i].setMap(null);
	}
}
function initTmap() {
	var map = new Tmapv2.Map("map_div", {
		center : new Tmapv2.LatLng(33.38260485180545, 126.57321166992229), // 지도 초기 좌표
		width : "100%",
		height : "925px",
//		zoomControl : false,
		zoom : 11
	});
	return map;
}
