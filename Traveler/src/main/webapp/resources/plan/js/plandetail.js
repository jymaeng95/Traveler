/**
 * 
 */
$(document).ready(function(){
	var finalDayList = [];
	var map = initTmap();
	$(".right-planlist").find('ul').each(function(){
		$(this).sortable();
		$(this).disableSelection();

	});
	$('.right-planlist').find('button').click(function(){
		var jsonData = new Array();
		var viaData = new Array();
		var result = $(this).siblings('ul').sortable('toArray');
		$(this).siblings('ul').sortable().find('input').each(function(){
			jsonData.push(JSON.parse($(this).val()));
		});

		addMarker(map,"start",jsonData[0]);
		for(var i=1;i<jsonData.length-1;i++){
			addMarker(map,"pass",jsonData[i]);
			viaData.push(jsonData[i]);
		}
		addMarker(map,"end",jsonData[jsonData.length-1]);

		testAPI(map,jsonData[0],jsonData[jsonData.length-1],viaData);
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
	var prtcl;
	var headers = {}; 
	let today =  new Date();
	var realTime;
	if(today.getMonth()+1 <10) realTime = today.getFullYear()+'0'+today.getMonth()+1+today.getDate()+today.getHours()+today.getMinutes();
	else realTime = today.getFullYear()+'0'+today.getMonth()+1+today.getDate()+today.getHours()+today.getMinutes();

	var viaPoints = new Array();
	var viaPoint = {};
	for(var i=0;i<viaInfo.length;i++){
		viaPoint.viaPointId = "경유지1";
		viaPoint.viaPointName = viaInfo.title;
		viaPoint.viaX = viaInfo.mapX;
		viaPoint.viaY = viaInfo.mapY;
		viaPoints.push(viaPoint);
	}
	headers["appKey"]="l7xx15e7f0ab6ce4456f9a97564f50cf5e2f";
	$.ajax({
		type:"POST",
		headers : headers,
		url:"https://apis.openapi.sk.com/tmap/routes/routeOptimization30?version=1&format=json",//
		async:false,
		contentType: "application/json",
		data: JSON.stringify({
//			"reqCoordType": "WGS84GEO",
//			"resCoordType" : "WGS84GEO",
			"startName": "출발",
			"startX": startInfo.mapX,
			"startY": startInfo.mapY,
			"startTime": realTime,
			"endName": "도착",
			"endX": endInfo.mapX,
			"endY": endInfo.mapY,
			"viaPoints": viaPoints
		}),
		success:function(response){
			prtcl = response;
			var style_red = {
					fillColor:"#FF0000",
					fillOpacity:0.2,
					strokeColor: "#FF0000",
					strokeWidth: 3,
					strokeDashstyle: "solid",
					pointRadius: 2,
					title: "this is a red line"
			};
			/* -------------- Geometry.Point -------------- */
			//경유지 최적화 결과 Line 그리기
			drawData(prtcl);

		},
		error:function(request,status,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function drawData(data){
	// 지도위에 선은 다 지우기
	routeData = data;
	var resultStr = "";
	var distance = 0;
	var idx = 1;
	var newData = [];
	var equalData = [];
	var pointId1 = "-1234567";
	var ar_line = [];

	for (var i = 0; i < data.features.length; i++) {
		var feature = data.features[i];
		//배열에 경로 좌표 저잘
		if(feature.geometry.type == "LineString"){
			ar_line = [];
			for (var j = 0; j < feature.geometry.coordinates.length; j++) {
				var startPt = new Tmapv2.LatLng(feature.geometry.coordinates[j][1],feature.geometry.coordinates[j][0]);
				ar_line.push(startPt);
				pointArray.push(feature.geometry.coordinates[j]);
			}
			var polyline = new Tmapv2.Polyline({
				path: ar_line,
				strokeColor: "#ff0000", 
				strokeWeight: 6,
				map: map
			});
			new_polyLine.push(polyline);
		}
		var pointId2 = feature.properties.viaPointId;
		if (pointId1 != pointId2) {
			equalData = [];
			equalData.push(feature);
			newData.push(equalData);
			pointId1 = pointId2;
		}
		else {
			equalData.push(feature);
		}
	}
	geoData = newData;
	var markerCnt = 1;
	for (var i = 0; i < newData.length; i++) {
		var mData = newData[i];
		var type = mData[0].geometry.type;
		var pointType = mData[0].properties.pointType;
		var pointTypeCheck = false; // 경유지 일때만 true
		if (mData[0].properties.pointType == "S") {
			var img = 'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_s.png';
			var lon = mData[0].geometry.coordinates[0];
			var lat = mData[0].geometry.coordinates[1];
		}
		else if (mData[0].properties.pointType == "E") {
			var img = 'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_e.png';
			var lon = mData[0].geometry.coordinates[0];
			var lat = mData[0].geometry.coordinates[1];
		}
		else {
			markerCnt=i;
			var lon = mData[0].geometry.coordinates[0];
			var lat = mData[0].geometry.coordinates[1];
		}	
	}
}