/**
 * 
 */
$(document).ready(function(){
	var finalDayList = [];
	var map = initTmap();
	var ex_marker = new Array();
	var ex_polyline = new Array();
	var allInfo = new Array();
	var schedule = new Array();
	var start = 9;
	var end = 10;
	var today = new Date();
	$('.right-planlist').find('ul').find('input').each(function(){
		allInfo.push(JSON.parse($(this).val()));
	});
	var firstDate = allInfo[0].planDate.split('-');
	for(var i=0;i<allInfo.length;i++){
		var data = {};
		data.text = allInfo[i].title;
		var date = allInfo[i].planDate.split('-');
		if(firstDate[0] != date[0] || firstDate[1] != date[1] || firstDate[2] != date[2]){
			firstDate = date;
			start = 9;
			end = 10;
		}
		data.startDate = new Date(date[0], (date[1]-1), date[2], start, 00);
		data.endDate = new Date(date[0], (date[1]-1), date[2], end, 00);
		console.log('start:'+start);
		console.log('end:'+end);
		start += 2; end+=2;
		schedule.push(data);
	}
	console.log(schedule);
	
	
	$(".right-planlist").find('ul').each(function(){
		$(this).sortable();
		$(this).disableSelection();
		
	});

	$('.right-planlist').find('button').click(function(){
		if(ex_marker != "") {
			for(var marker of ex_marker)
				marker.setMap(null);
		}
		if(ex_polyline != "") {
			for(var line of ex_polyline)
				line.setMap(null);
		}
		var jsonData = new Array();
		var viaData = new Array();
		var result = $(this).siblings('ul').sortable('toArray');
		$(this).siblings('ul').sortable().find('input').each(function(){
			jsonData.push(JSON.parse($(this).val()));
		});

		ex_marker.push(addMarker(map,"start",jsonData[0]));
		for(var i=1;i<jsonData.length-1;i++){
			ex_marker.push(addMarker(map,"pass",jsonData[i]));
			viaData.push(jsonData[i]);
		}
		ex_marker.push(addMarker(map,"end",jsonData[jsonData.length-1]));

		ex_polyline = testAPI(map,jsonData[0],jsonData[jsonData.length-1],viaData);
		
	});


	var instance = $("#scheduler").dxScheduler({
		dataSource: schedule,
		views: ["day","week", "agenda"],
		currentView: "week", 
		currentDate: new Date(today.getFullYear(), today.getMonth(), today.getDate()),
		startDayHour: 9,
		height: 600,
		editing: {
			allowDeleting:false
		},
		onAppointmentAdded: function(e){  
			console.log(e.appointmentData);  
		} 
	}).dxScheduler("instance");
	$("#test").click(function(){
		var data = instance.getDataSource().items();
		console.log("instance2 : " + data[0].text);
	});
});

function initTmap(){
	var map = new Tmapv2.Map("map_div",  
			{
		center: new Tmapv2.LatLng(33.38260485180545, 126.57321166992229), // 지도 초기 좌표
		width: "100%", 
		height: "400px",
		zoom: 11
			});

	return map;
} 

function addMarker(map,status,planInfo){
	var markerLayer;
	switch (status) {
	case "start":
		imgURL = 'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_s.png';
		break;
	case "pass":
		imgURL = 'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_p.png';
		break;
	case "end":
		imgURL = 'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_e.png';
		break;
	default:
	};
	var marker = new Tmapv2.Marker({
		position: new Tmapv2.LatLng(planInfo.mapX,planInfo.mapY),
		icon: imgURL,
		map: map
	});

	return marker;
}
function testAPI(map,startInfo,endInfo,viaInfo){
	var line_arr = new Array();
	var headers = {}; 
	let today =  new Date();
	var realTime;
	if((today.getMonth()+1) <10) realTime = today.getFullYear()+'0'+(today.getMonth()+1)+today.getDate()+today.getHours()+today.getMinutes();
	else realTime = today.getFullYear()+(today.getMonth()+1)+today.getDate()+today.getHours()+today.getMinutes();

	var viaPoints = new Array();
	for(var i=0;i<viaInfo.length;i++){
		var viaPoint = {};
		viaPoint.viaPointId = "경유지"+(i+1);
		viaPoint.viaPointName = viaInfo[i].title;
		viaPoint.viaX = viaInfo[i].mapY;
		viaPoint.viaY = viaInfo[i].mapX;
		viaPoints.push(viaPoint);
	}
	headers["appKey"]="l7xx15e7f0ab6ce4456f9a97564f50cf5e2f";
	$.ajax({
		type:"POST",
		headers : headers,
		url:"https://apis.openapi.sk.com/tmap/routes/routeOptimization10?version=1&format=json",//
		async:false,
		contentType: "application/json",
		data: JSON.stringify({
			"startName": "출발",
			"startX": startInfo.mapY,
			"startY": startInfo.mapX,
			"startTime": realTime,
			"endName": "도착",
			"endX": endInfo.mapY,
			"endY": endInfo.mapX,
			"viaPoints": viaPoints
		}),
		success:function(response){
			var resultData = response.properties;
			var resultFeatures = response.features;

			// 결과 출력
			var tDistance = "총 거리 : " + (resultData.totalDistance/1000).toFixed(1) + "km,  ";
			var tTime = "총 시간 : " + Math.round((resultData.totalTime/60)) + "분,  ";
			var tFare = "총 요금 : " + resultData.totalFare + "원";

			$("#result").text(tDistance+tTime+tFare);
			for(var i in resultFeatures) {
				var geometry = resultFeatures[i].geometry;
				var properties = resultFeatures[i].properties;
				var polyline_;

				drawInfoArr = [];

				if(geometry.type == "LineString") {
					for(var j in geometry.coordinates){
						var point = new Tmapv2.LatLng(geometry.coordinates[j][1],geometry.coordinates[j][0]);

						drawInfoArr.push(point);
					}

					polyline_ = new Tmapv2.Polyline({
						path : drawInfoArr,
						strokeColor : "#FF0000",
						strokeWeight: 6,
						map : map
					});
					line_arr.push(polyline_)
				}
			}
		},
		error:function(request,status,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	return line_arr;
}