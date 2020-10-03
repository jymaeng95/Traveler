$(document).ready(function(){

	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);

	
	google.charts.load('current', {'packages':['bar']});
	google.charts.setOnLoadCallback(drawChart2);

	
});
$(document).on("click","btn-graph",function(){
	$("#board-list").css("display:none;");
	$("#board-search").css("display:none;");
	$("#budget-stastic").css("display:block;");
	
	$.ajax({
		url : "/budget/index/graph",
		type:"get",
		dataType:"json",
		success : function(data){
			
		},
		error : function(error){
			alert("그래프 에러 ")
		}
	});
});
function drawCategoryChart(budget) {
	var catCount = [0,0,0,0,0]
	for(bg of budget){
		switch(bg.cat){
		case "관광" : catCount[0]++; break;
		case "숙박" : catCount[1]++; break;
		case "식비" : catCount[2]++; break;
		case "교통" : catCount[3]++; break;
		case "기타" : catCount[4]++; break;
		}
	}
	
	var data = google.visualization.arrayToDataTable([
		['Task', 'Hours per Day'],
		['관광',catCount[0]],
		['숙박',catCount[1]],
		['식비',catCount[2]],
		['교통', catCount[3]],
		['기타',catCount[4]]
		]);

	var options = {
			title: '카테고리 별 비율',
			chartArea:{left:20,top:0,width:'100%',height:'100%'}
	};

	var chart = new google.visualization.PieChart(document.getElementById('piechart'));

	chart.draw(data, options);
}

function drawBudgetChart(budget) {
	var maxIncome=0;
	var maxTotal=0;
	var maxExpend=0;
	
	var minIncome=0;
	var minExpend=0;
	var minTotal=0;
	
	var avgIncome=0;
	var avgExpend=0;
	var avgTotal=0;
	
	for(bg of budget) {
		
	}
	var data = google.visualization.arrayToDataTable([
		['Year', '수입', '지출', '총계'],
		['평균', 1000, 400, 200],
		['최대', 1170, 460, 250],
		['최소', 660, 1120, 300],
		]);

	var options = {
			chart: {
				title: 'Company Performance',
				subtitle: 'Sales, Expenses, and Profit: 2014-2017',
				chartArea:{left:20,top:0,width:'100%',height:'100%'}
			}
	};

	var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

	chart.draw(data, google.charts.Bar.convertOptions(options));
}

