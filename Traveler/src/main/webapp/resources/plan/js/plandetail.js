/**
 * 
 */
$(document).ready(function(){
	var map = initTmap();
	var ex_marker = new Array();
	var ex_polyline = new Array();
	var allInfo = new Array();
	var schedule = new Array();
	var start = 9;
	var end = 10;
	var userId = $("#uid").val();
	var planTitle = $("#p_title").text();
	var planNo = $("#planno").val();
	$('.right-planlist').find('ul').find('input').each(function(){
		allInfo.push(JSON.parse($(this).val()));
	});
	var firstDate = allInfo[0].planDate.split('-');
	for(var i=0;i<allInfo.length;i++){
		console.log(allInfo[i]);
		var data = {};
		var date;
		data.text = allInfo[i].title;
		if(allInfo[i].startDate =="" || allInfo[i].endDate==""){
			date = allInfo[i].planDate.split('-');
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
		}else {
			date = allInfo[i].startDate.split('-');
			data.startDate = new Date(date[0], (date[1]-1), date[2], date[3], date[4]);
			date = allInfo[i].endDate.split('-');
			data.endDate = new Date(date[0], (date[1]-1), date[2], date[3], date[4]);
			data.descript = allInfo[i].descript;
			schedule.push(data);
		}
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
		$(this).siblings('ul').sortable().find('input[name=planData]').each(function(){
			jsonData.push(JSON.parse($(this).val()));
		});
		if(jsonData.length >2) {
			ex_marker.push(addMarker(map,"start",jsonData[0]));
			for(var i=1;i<jsonData.length-1;i++){
				ex_marker.push(addMarker(map,"pass",jsonData[i]));
				viaData.push(jsonData[i]);
			}
			ex_marker.push(addMarker(map,"end",jsonData[jsonData.length-1]));
			ex_polyline = testAPI(map,jsonData[0],jsonData[jsonData.length-1],viaData);
		}else {
			alert("경유지가 최소 한개 이상이어야합니다.");
		}
	});
	var calendarDate = allInfo[0].planDate.split('-');
	var instance = $("#scheduler").dxScheduler({
		dataSource: schedule,
		views: ["week", "agenda"],
		currentView: "week", 
		currentDate: new Date(calendarDate[0], (calendarDate[1]-1), calendarDate[2]),
		startDayHour: 9,
		showAllDayPanel : false,
		height: 600,
		editing: {
			allowDeleting:false
		},
		onAppointmentAdded: function(e){  
			console.log(e.appointmentData);  
		} 
	}).dxScheduler("instance");


	$("#btn-save").click(function(){
		var s_instance = instance.getDataSource().items();
		var totalDate = getBetweenDate(getMaxTime(s_instance), getMinTime(s_instance));
		var lastDate = getMinTime(s_instance);
		var finalData = new Array();
		var buffer;

		console.log(lastDate);
		console.log(totalDate);
		for(var i=0;i<s_instance.length;i++){
			var flag = false;
			buffer = {};
			for(var j=0;j<allInfo.length;j++){
				if(allInfo[j].title == s_instance[i].text){
					buffer.userId = userId;
					buffer.planNo = planNo;
					buffer.title = allInfo[j].title;
					var date = s_instance[i].startDate;
					buffer.startDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+date.getHours()+"-"+(date.getMinutes()<10?'0':'')+date.getMinutes();
					date = s_instance[i].endDate;
					buffer.endDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+date.getHours()+"-"+(date.getMinutes()<10?'0':'')+date.getMinutes();
					if(s_instance[i].descript != "") buffer.descript = s_instance[i].descript;
					else buffer.descript = "";
					buffer.planTitle = planTitle;
					buffer.planDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
					buffer.planDay = getDay(s_instance[i].startDate,lastDate,totalDate);
					buffer.planTotalDate = totalDate;
					buffer.is_insertAfter = allInfo[j].is_insertAfter;
					finalData.push(buffer);
					flag = true;
				}
			}
			if(!flag){
				buffer.userId = userId;
				buffer.planNo = planNo;
				buffer.title =	s_instance[i].text;
				var date = s_instance[i].startDate;
				buffer.startDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+date.getHours()+"-"+(date.getMinutes()<10?'0':'')+date.getMinutes();
				date = s_instance[i].endDate;
				buffer.endDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+date.getHours()+"-"+(date.getMinutes()<10?'0':'')+date.getMinutes();
				buffer.descript = s_instance[i].descript;
				buffer.planDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
				buffer.planDay = getDay(s_instance[i].startDate,lastDate,totalDate);
				buffer.planTotalDate = totalDate;
				buffer.planTitle = planTitle;
				buffer.is_insertAfter = 'Y';
				finalData.push(buffer);
			}
		}

		sendSchedule(finalData);
	});
});
function sendSchedule(finalData){
	var frmData = {"data" : finalData};
	$("#schedule-plan").val(JSON.stringify(frmData));
	$("#scheduleForm").submit();
}

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
	var realTime = getRealTime();

	var viaPoints = new Array();
	for(var i=0;i<viaInfo.length;i++){
		var viaPoint = {};
		viaPoint.viaPointId = "경유지"+(i+1);
		viaPoint.viaPointName = viaInfo[i].title;
		viaPoint.viaX = viaInfo[i].mapY;
		viaPoint.viaY = viaInfo[i].mapX;
		viaPoints.push(viaPoint);
	}

	console.log(viaPoints[0]);
	console.log(startInfo);
	console.log(endInfo);

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
function getBetweenDate(start,end){
//	btDay = btMs / (1000*60*60*24) + 1; // 두 날짜 차이 계산
	var startTime = new Date(start.getFullYear(),start.getMonth(),start.getDate());
	var endTime = new Date(end.getFullYear(),end.getMonth(),end.getDate());

	var betweenTime = endTime.getTime()-startTime.getTime();
	var result = betweenTime / (1000*60*60*24) +1;	
	return result;
}

function getDay(base, last,totalDate) {
	var startTime = new Date(base.getFullYear(), base.getMonth(), base.getDate());
	var endTime = new Date(last.getFullYear(), last.getMonth(), last.getDate());

	var betweenTime = endTime.getTime()-startTime.getTime();
	var day = betweenTime / (1000*60*60*24) + 1	;

	return (totalDate - day)+1;
}

function getMaxTime(instance){
	var maxTime = instance[0].endDate.getTime();
	var max = instance[0].endDate;
	for(var i=1;i<instance.length;i++){
		if(maxTime>instance[i].endDate.getTime()) {
			maxTime = instance[i].endDate.getTime();
			max = instance[i].endDate;
		}
	}
	return max;
}

function getMinTime(instance){
	var minTime = instance[0].startDate.getTime();
	var min = instance[0].startDate;
	for(var i=1;i<instance.length;i++){
		if(minTime<instance[i].startDate.getTime()) {
			minTime = instance[i].startDate.getTime();
			min = instance[i].startDate;
		}
	}
	return min;
}

function getRealTime(){
	var today = new Date();
	var year = today.getFullYear();
	var month = ((today.getMonth()+1)<10?'0':'')+(today.getMonth()+1);
	var date = (today.getDate()<10?'0':'')+today.getDate();
	var hour = (today.getHours()<10?'0':'')+today.getHours();
	var min = (today.getMinutes()<10?'0':'')+today.getMinutes();
	var realTime = year+month+date+hour+min;
	return realTime;
}