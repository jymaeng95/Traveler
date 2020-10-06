$(document).ready(function(){


});
$(document).on("click","#btn-back-budget",function(){
	$("#board-list").attr("style","display:block;");
	$("#board-search").attr("style","display:block;");
	$("#budget-stastic").attr("style","display:none;");
})

$(document).on("click","#btn-graph",function(){
	$("#board-list").attr("style","display:none;");
	$("#board-search").attr("style","display:none;");


	$.ajax({
		url : "/budget/index/graph",
		type:"get",
		dataType:"json",
		success : function(data){
			google.charts.load('current', {'packages':['corechart']});
			google.charts.setOnLoadCallback(drawCategoryChart);
			
			google.charts.load('current', {'packages':['bar']});
			google.charts.setOnLoadCallback(drawBudgetChart);
			function drawCategoryChart() {
				console.log(data.catData);
				var dataChart = google.visualization.arrayToDataTable([
					['Category', 'Count'],
					[data.catData[0].CAT,data.catData[0].COUNT_CAT],
					[data.catData[1].CAT,data.catData[1].COUNT_CAT],
					[data.catData[2].CAT,data.catData[2].COUNT_CAT],
					[data.catData[3].CAT,data.catData[3].COUNT_CAT],
					[data.catData[4].CAT,data.catData[4].COUNT_CAT],
					[data.catData[5].CAT,data.catData[5].COUNT_CAT]
					]);

				var options = {
						title: '카테고리 별 비율',
						chartArea:{left:20,top:30, width:'100%',height:'100%'},
						colors:['#facb7a','#78b6fa','#fa5fd3','#AEFA9D','#D8A5FA','#F7D2C2']
				};

				var chart = new google.visualization.PieChart(document.getElementById('cat-chart'));

				chart.draw(dataChart, options);
			}
			
			function drawBudgetChart() {
				console.log(data.budgetData)
				var dataChart = google.visualization.arrayToDataTable([
					['내용', '수입', '지출', '총계',],
					['평균', data.budgetData[0].AVG_INC,data.budgetData[0].AVG_EXP,data.budgetData[0].AVG_TOTAL],
					['최대', data.budgetData[0].MAX_INC,data.budgetData[0].MAX_EXP,data.budgetData[0].MAX_TOTAL],
					['최소', data.budgetData[0].MIN_INC,data.budgetData[0].MIN_EXP,data.budgetData[0].MIN_TOTAL],
					]);

				var options = {
						chart: {
							title: '회원 예산 추이',
							subtitle: '예산을 공개한 회원들의 예상 예산',
							chartArea:{left:20,width:'100%',height:'100%'},
						},
						vAxis: {
							scaleType: 'mirrorLog',
							format:'decimal'
							},
						colors:['#facb7a','#78b6fa','#fa5fd3'],
				};

				var chart = new google.charts.Bar(document.getElementById('budget-chart'));

				chart.draw(dataChart, google.charts.Bar.convertOptions(options));
			}
			alert("그래프 테스트");
			$("#budget-stastic").fadeIn(1000);
		},
		error : function(error){
			alert("그래프 에러 ")
		}
	});
});


