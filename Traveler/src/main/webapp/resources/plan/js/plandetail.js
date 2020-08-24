/**
 * 
 */
$(document).ready(function(){
	var map = initTmap();
});

function initTmap(){
	var map = new Tmapv2.Map("map_div",  
			{
		center: new Tmapv2.LatLng(37.566481622437934,126.98502302169841), // 지도 초기 좌표
		width: "100%", 
		height: "400px",
		zoom: 15
			});
	
	return map;
} 