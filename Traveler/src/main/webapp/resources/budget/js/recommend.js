$(document).ready(function(){
	$(".plan-each").on("click",function(){
		var planNo = $(this).find(".planNo").text();

		var form = document.createElement("form");
		form.setAttribute("method", "GET");  //Post 방식
		form.setAttribute("action", "/budget/read"); //요청 보낼 주소

		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "planNo");
		hiddenField.setAttribute("value", planNo);
		form.appendChild(hiddenField);

		document.body.appendChild(form);
		console.log(planNo);
		form.submit();
	});
	
	$(".plan-recommend").on("click",function(){
		var planNo = $(this).find(".recommend-planno").text();
		var type = $(this).find(".recommend-standard").text();
		var form = document.createElement("form");
		form.setAttribute("method", "GET");  //Post 방식
		form.setAttribute("action", "/budget/read"); //요청 보낼 주소

		var planNoField = document.createElement("input");
		planNoField.setAttribute("type", "hidden");
		planNoField.setAttribute("name", "planNo");
		planNoField.setAttribute("value", planNo);
		
		form.appendChild(planNoField);

		var typeField = document.createElement("input");
		typeField.setAttribute("type", "hidden");
		typeField.setAttribute("name", "recommend");
		typeField.setAttribute("value", type);		
		form.appendChild(typeField);
		
		document.body.appendChild(form);
		console.log(planNo);
		form.submit();
	});
	
	var actionForm = $("#actionForm");
	$(".paginate_button a").on("click",function(e){
		e.preventDefault();
		console.log('click');
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	 });
});
$(document).on("click","#btn-back-budget",function(){
	$("#board-list").attr("style","display:block;");
	$("#recommend-list").attr("style","display:block;");
	$("#budget-stastic").attr("style","display:none;");
})

$(document).on("click","#btn-graph",function(){
	$("#recommend-list").attr("style","display:none");
	$("#board-list").attr("style","display:none;");
	var planTotalDate = $(this).val();

	$.ajax({
		url : "/budget/recommend/graph",
		type:"get",
		dataType:"json",
		data :{"planTotalDate" : planTotalDate},
		success : function(data){
			google.charts.load('current', {'packages':['corechart']});
			google.charts.setOnLoadCallback(drawCategoryChart);

			google.charts.load('current', {'packages':['bar']});
			google.charts.setOnLoadCallback(drawBudgetChart);
			function drawCategoryChart() {
				console.log(data.catData);

				var dataChart = new google.visualization.DataTable();
				dataChart.addColumn('string','Category');
				dataChart.addColumn('number','Count');
				dataChart.addRows(data.catData.length);
				for(var i=0;i<data.catData.length;i++){
					dataChart.setCell(i,0,data.catData[i].CAT)
					dataChart.setCell(i,1,data.catData[i].COUNT_CAT);
				}
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
					['평균', Math.round(data.budgetData[0].AVG_INC),Math.round(data.budgetData[0].AVG_EXP),Math.round(data.budgetData[0].AVG_TOTAL)],
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
			$("#budget-stastic").fadeIn(1000);
		},
		error : function(error){
			alert("그래프 에러 ")
		}
	});
});


