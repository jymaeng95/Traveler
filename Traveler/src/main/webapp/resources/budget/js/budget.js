
var balance = 0;


var load = function(data) {  
	clearTrans();
	balance = 0;
	startAppend(data);
//	balanceCalc(balance);  
	noTrans();
};

//append trans data
function startAppend(data) {
	for (var i = 0; i < data.length; i++) {
		if(data[i].income > 0){
			$(".trans-list").append(
					'<div class="trans-each">\
					<div class="trans-details">\
					<span class="action"></span>\
					<h3 class="trans-title">' +
					data[i].title +
					'</h3><div class="row">\
					<h5 class="trans-descript">' +
					data[i].descript + '</h5>\
					<h5 class="trans-cat">'+data[i].cat+'</h5>\
					<h5 class="trans-planDate">'+data[i].planDate+
					'</h5></div>\
					</div>\
					<div class="trans-price">\
					<h4 class="trans-price income" style="color:rgb(113, 207, 66)">'+data[i].income+'\\\
					</h4>\
					</div>\
					</div><hr>'
			).show('fast');
		}else if(data[i].expend > 0){
			$(".trans-list").append(
					'<div class="trans-each">\
					<div class="trans-details">\
					<span class="action"></span>\
					<h3 class="trans-title">' +
					data[i].title +
					'</h3><div class="row">\
					<h5 class="trans-descript">' +
					data[i].descript + '</h5>\
					<h5 class="trans-cat">'+data[i].cat+'</h5>\
					<h5 class="trans-planDate">'+data[i].planDate+
					'</h5></div>\
					</div>\
					<div class="trans-price">\
					<h4 class="trans-price expend" style="color:#ff3232">'+data[i].expend+'\\\
					</h4>\
					</div>\
					</div><hr>'
			).show('fast');
		} else {
			$(".trans-list").append(
					'<div class="trans-each">\
					<div class="trans-details">\
					<span class="action"></span>\
					<h3 class="trans-title">' +
					data[i].title +
					'</h3><div class="row">\
					<h5 class="trans-descript">' +
					data[i].descript + '</h5>\
					<h5 class="trans-cat">'+data[i].cat+'</h5>\
					<h5 class="trans-planDate">'+data[i].planDate+
					'</h5></div>\
					</div>\
					<div class="trans-price">\
					<h4 class="trans-price">0\\\
					</h4>\
					</div>\
					</div><hr>'
			).show('fast');
		}
		noTrans();
	}    
	detachAddBtn();
}

//clear trans list
var clearTrans = function(){
	$(".trans-list").empty().hide();
}

//clear modal
var clearModal = function() {
	$('#price').val("");
	$("#info").val("");
	$('input[name="cat"]').attr("checked","false");
}

var clearAddModal = function() {
	$('#add-title').val("")
	$('#add-date').val("");
	$('#add-price').val("");
	$("#add-info").val("");
	$('input[name="add-cat"]').attr("checked","false");
}
//no trans
var noTrans = function() {
	$('.trans-list').children().length == 0 ? $(".trans-list").append('<h5 class="no-trans"> No transactions for this card</h5>').show('fast') :"";
}

//load inital data
$(document).ready(function() {    
	$(".cc:eq(0)").addClass("cc-active")  
	var planNo = $(".cc:eq(0)").find('input').val();
	$("#planNo").val(planNo);
	var title = $(".cc:eq(0)").find('div.cc-num').text();
	$("#planTitle").text(title);
	var data = getUserBudget(planNo);
	var planData = getUserPlanDate(planNo);
	var firstDate = planData.firstDate.split("-");
	var startDate = new Date(firstDate[0], (firstDate[1]-1), firstDate[2]);
	var totalDate= planData.totalDate;
	console.log(data);
	$("#add-date").flatpickr({
		minDate: startDate,
		maxDate: startDate.fp_incr(totalDate-1)
	});
	load(data) ;
});

$(document).on("click","#btn-save",function(){
	var planNo = $("#planNo").val();
	var budget = setBudgetData(planNo);
	console.log(budget);
	$.ajax({
		url : "/budget/save/budget",
		dataType: "json",
		type : "post",
		data : JSON.stringify(budget),
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			alert("저장 완료!");
			location.reload(true);
		},
		error : function(error){
			alert("저장 실패 ");
		}
	});
});

$(document).on("click","#btn-graph",function(){
	$(".trans-list").attr("style","display:none;");
	$(".trans-graph").attr("style","display:block;");
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	var catData = getCatInfo();
	//{"unknown" : catData[0], "sight" : catData[1], "hotel" : catData[2],
//	"food" : catData[3], "transport" : catData[4], "etc" : catData[5]};
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
			['Category', 'Count'],
			['관광', catData.sight],
			['숙박', catData.hotel],
			['교통', catData.transport],
			['식비', catData.food],
			['기타', catData.etc]
			]);
		var options = {
				title: '카테고리 별 지출/수입 현황',
				chartArea:{left:'20px', width:'100%',height:'85%'},
				colors : ['#5b6777','#f15628','#ffc81b','#1ca392','#57b4c1'],
				
		};

		var chart = new google.visualization.PieChart(document.getElementById('piechart'));

		chart.draw(data, options);
	}
});

$(document).on("click","#btn-trans",function(){
	$(".trans-graph").attr("style","display:none");
	$(".trans-list").attr("style","display:block");
});

//close modal
$(document).on("click", ".modal-close", function(e) {
	$(".modal").hide();
	clearModal();
});

//close add Modal
$(document).on("click",".add-modal-close", function(e){
	$(".add-budget-modal").hide();
	clearAddModal();
});

$(document).on("mouseenter",".trans-each",function(){
	$(this).append("<span>***</span>");
});
$(document).on("mouseleave",".trans-each",function(){
	$(this).find("span").last().remove();
});

//각각 영역 클릭시 상세정보 입력 모달 
$(document).on("click",".trans-each",function(){
	var modalData;
	var title = $(this).find('h3').text();
	var descript = $(this).find('h5.trans-descript').text();
	var cat = $(this).find('h5.trans-cat').text();
	var price = $(this).find('h4.trans-price').text().split("\\");
	if(cat != "카테고리") {
		switch(cat){
		case "관광" : $("input[value='관광']").attr("checked",true); break;
		case "숙박" : $("input[value='숙박']").attr("checked",true); break;
		case "식비" : $("input[value='식비']").attr("checked",true); break;
		case "교통" : $("input[value='교통']").attr("checked",true); break;
		case "기타" : $("input[value='기타']").attr("checked",true); break;
		}
	}
	$("#title").text(title);
	$("#info").val(descript);
	$("#price").val(price[0]);
	$(".modal").show();
});

//click listener for active card
$(document).on("click", ".cc", function(e) {
	$(".cc").removeClass("cc-active");
	$(this).addClass("cc-active");
	$(".trans-graph").attr("style","display:none");
	var planNo = $(this).find('input').val();
	$("#planNo").val(planNo);
	var title = $(this).find('div.cc-num').text();
	$("#planTitle").text(title);
	var data = getUserBudget(planNo);
	var planData = getUserPlanDate(planNo);
	var firstDate = planData.firstDate.split("-");
	var startDate = new Date(firstDate[0], (firstDate[1]-1), firstDate[2]);
	var totalDate= planData.totalDate;
	$("#add-date").flatpickr({
		minDate: startDate,
		maxDate: startDate.fp_incr(totalDate-1)
	});
	load(data);
});

//모달에서 수입 버튼 클릭 시 
$(document).on("click","#income",function(){
	if(!checkModal()){
		alert("빈칸을 채워주세요");
		return false;
	}
	$(".trans-each").each(function(){
		if($(this).find("h3").text() == $("#title").text()){
			$(this).find('h5.trans-descript').text($("#info").val());
			$(this).find('h5.trans-cat').text($("input[name='cat']:checked").val());
			$(this).find('h4.trans-price').text($("#price").val()+"\\");
			$(this).find('h4.trans-price').css("color", "rgb(113, 207, 66)");
			if($(this).find('h4.trans-price').hasClass("expend")) 
				$(this).find('h4.trans-price').removeClass("expend");
			$(this).find('h4.trans-price').addClass("income");
		}
	});
	$(".modal").hide();
	clearModal();
});

//모달 지출 버튼 클릭 시 
$(document).on("click","#expend",function(){
	if(!checkModal()){
		alert("빈칸을 채워주세요");
		return false;
	}
	$(".trans-each").each(function(){
		if($(this).find("h3").text() == $("#title").text()){
			$(this).find('h5.trans-descript').text($("#info").val());
			$(this).find('h5.trans-cat').text($("input[name='cat']:checked").val());
			$(this).find('h4.trans-price').text($("#price").val()+"\\");
			$(this).find('h4.trans-price').css("color", "#ff3232");
			if($(this).find('h4.trans-price').hasClass("income")) 
				$(this).find('h4.trans-price').removeClass("income");
			$(this).find('h4.trans-price').addClass("expend");
		}
	});
	$(".modal").hide();
	clearModal();
});

//새로운 계획 추가에서 수입 클릭 시 
$(document).on("click","#add-income",function(){
	var title = $("#add-title").val();
	var descript = $("#add-info").val();
	var planDate = $("#add-date").val();
	var cat = $("input[name='add-cat']:checked").val();
	var price = $("#add-price").val();
	if(!checkAddModal()){
		alert("빈칸을 채워주세요");
		return false;
	}
	addlist = $("#add-list").detach();
	$(".trans-list").append(
			'<div class="trans-each">\
			<div class="trans-details">\
			<span class="action"></span>\
			<h3 class="trans-title">' +
			title+
			'</h3><div class="row">\
			<h5 class="trans-descript">' +
			descript + '</h5>\
			<h5 class="trans-cat">'+cat+'</h5>\
			<h5 class="trans-planDate">'+planDate+
			'</h5></div>\
			</div>\
			<div class="trans-price">\
			<h4 class="trans-price income" style="color:rgb(113, 207, 66);">'+price+"\\"+'\
			</h4>\
			</div>\
			</div><hr>'
	).show('fast');
	$(".trans-list").append(addlist);
	$(".add-budget-modal").hide();
	clearAddModal();
});

//새로운 예산에서 지출 버튼 클릭 시 
$(document).on("click","#add-expend",function(){
	var title = $("#add-title").val();
	var descript = $("#add-info").val();
	var planDate = $("#add-date").val();
	var cat = $("input[name='add-cat']:checked").val();
	var price = $("#add-price").val();
	if(!checkAddModal()){
		alert("빈칸을 채워주세요");
		return false;
	}
	addlist = $("#add-list").detach();
	$(".trans-list").append(
			'<div class="trans-each">\
			<div class="trans-details">\
			<span class="action"></span>\
			<h3 class="trans-title">' +
			title+
			'</h3><div class="row">\
			<h5 class="trans-descript">' +
			descript + '</h5>\
			<h5 class="trans-cat">'+cat+'</h5>\
			<h5 class="trans-planDate">'+planDate+
			'</h5></div>\
			</div>\
			<div class="trans-price">\
			<h4 class="trans-price expend" style="color:#ff3232;">'+price+"\\"+'\
			</h4>\
			</div>\
			</div><hr>'
	).show('fast');
	$(".trans-list").append(addlist);
	$(".add-budget-modal").hide();
	clearAddModal();

});

//새로운 예산 (+) 버튼 클릭 
$(document).on("click","#add-list",function(){
	$(".add-budget-modal").show();
});

//계산 버튼 클릭 시 
$(document).on("click","#btn-calc",function(){
	$("#balance").text("총계 : "+calcBalance()+"원");
});

function getCatInfo(){
	//정보(미정) 관광, 숙박 식비 교통 기타 
	var catCount = [0,0,0,0,0]
	$(".trans-cat").each(function(){
		cat = $(this).text();
		switch(cat){
		case "관광" : catCount[0]++; break;
		case "숙박" : catCount[1]++; break;
		case "식비" : catCount[2]++; break;
		case "교통" : catCount[3]++; break;
		case "기타" : catCount[4]++; break;
		}
	});
	var catData = {"sight" : catCount[0], "hotel" : catCount[1],
			"food" : catCount[2], "transport" : catCount[3], "etc" : catCount[4]};
	console.log(catData);
	return catData;
}

function calcBalance(){
	var sum = 0;
	var income = 0;
	var expend = 0;
	$(".income").each(function(){
		incomeText = $(this).text().split("\\");
		income += parseInt(incomeText[0],10);
	})
	$(".expend").each(function(){
		expendText = $(this).text().split("\\");
		expend -= parseInt(expendText[0],10);
	})
	sum = income + expend;
	return sum;
}

function getUserPlanDate(planNo) {
	var result = new Object();
	$.ajax({
		url : "/budget/load/date",
		async : false,
		dataType : "json",
		type : "get",
		data : {"planNo" : planNo },
		success : function(data) {
			result.firstDate = data.planDate;
			result.totalDate = data.planTotalDate;
		},
		error : function(error){
			alert("날짜 불러오기 오류");
		}
	});
	return result;
}

function getUserBudget(planNo){
	var result = new Array();
	$.ajax({
		url : "/budget/load/userplan",
		async : false,
		dataType: "json",
		type : "get",
		data : {"planNo" : planNo},
		success : function(data) {
			$.each(data,function(){
				var jsondata = new Object();
//				jsondata.title = data[i].title;
				jsondata.title = data.budget.title;
//				jsondata.planDate = data[i].planDate;
				jsondata.planDate = data.budget.planDate;
//				if(data[i].descript !="")
//					jsondata.descript = data[i].descript;
//				else 
//					jsondata.descript = "정보";
//				
				if(data.budget.desciprt!="")
					jsondata.descript = data.budget.descript;
				else
					jsondata.descript = "정보";
				
//				if(data[i].cat!="")
//					jsondata.cat = data[i].cat;
//				else 
//					jsondata.cat = "카테고리";
//				
				if(data.budget.cat != "")
					jsondata.cat = data.budget.cat;
				else 
					jsondata.cat = "카테고리";
				
				if (data.budget.income > 0){
					jsondata.income = data.budget.income;
					jsondata.expend = 0;
				} else {
					jsondata.income = 0;
					jsondata.expend = data.budget.expend;
				}
//				if(data[i].income > 0){
//					jsondata.income = data[i].income;
//					jsondata.expend = 0;
//				}else {
//					jsondata.income = 0;
//					jsondata.expend = data[i].expend;
//				}
				jsondata.fee = data.fee;
				result.push(jsondata); 
			});
		},
		error : function(error){
			alert("생성할 예산이 존재하지 않습니다.");
			location.href = "/";
		}
	});

	for(var i=0;i<result.length;i++){
		console.log(result[i]);
	}
	return result;
}

function detachAddBtn(){
	$(".trans-list").append(
			'<div id="add-list" style="text-align:center">\
			<span><i class="fas fa-2x fa-plus"></i></span>	\
			</div>'	
	)
}

function setBudgetData(planNo){
	var transactions = new Array();
	var trans;
	var budget = new Object();
	$(".trans-each").each(function(){
		trans = new Object();
		//title,cat, income,expend, total, descript, plandate
		//trans-title trans-cat income, expend, balance 
		trans.title = $(this).find('h3.trans-title').text();
		trans.cat = $(this).find('h5.trans-cat').text();

		if($(this).find('h4.trans-price').hasClass('income') == true) {
			trans.income = $(this).find('h4.income').text().split("\\")[0];
			trans.expend = 0;
		} else if($(this).find('h4.trans-price').hasClass('expend') == true) {
			trans.income = 0;
			trans.expend = $(this).find('h4.expend').text().split("\\")[0];
		} else {
			trans.income = 0;
			trans.expend = 0;
		}
		trans.descript = $(this).find('h5.trans-descript').text();
		trans.planDate = $(this).find('h5.trans-planDate').text();
		transactions.push(trans);
	});
	budget.planNo = planNo;
	budget.total = calcBalance();
	budget.transactions = transactions;

	return budget;
}

function onlyNumber(){
	if((event.keyCode<48)||(event.keyCode>57))
		event.returnValue=false;
}

function checkAddModal() {
	if($('#add-title').val()==""||$('#add-date').val()==""||$('#add-price').val()==""||
			$("#add-info").val()==""){
		return false;
	}
	return true;
}
function checkModal() {
	if($('#price').val()==""||$("#info").val()==""){
		return false;
	}
	return true;
}

