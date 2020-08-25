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
			var result = $(this).siblings('ul').sortable('toArray',{attribute : 'value'});
			alert('value : '+result);
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