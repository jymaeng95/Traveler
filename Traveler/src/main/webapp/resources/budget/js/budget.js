/* Josh Channin
   Fake data from generatedata.com
 */
//vars
var balance = 0;

////append cc to modal
//var ccModalAppend= function(ccType, ccNum, month, year) {
//if(ccType == 'amex') {	
//$(".cc-select").prepend(
//'<div class="cc ' +
//ccType +
//' cc-active">\
//<div class="cc-img-main"></div>\
//<div class="cc-num">**** *******  ' +
//ccNum +
//'</div>\
//<div class="cc-date">Valid Thru: ' +
//month +
//"/" +
//year +
//"</div>\
//</div>"
//);
//} else {
//$(".cc-select").prepend(
//'<div class="cc ' +
//ccType +
//' cc-active">\
//<div class="cc-img-main"></div>\
//<div class="cc-num">**** **** **** ' +
//ccNum +
//'</div>\
//<div class="cc-date">Valid Thru: ' +
//month +
//"/" +
//year +
//"</div>\
//</div>"
//);
//}	
//}


//append credit cards
//var ccAppend = function(data) {
//$(".cc-select").append(
//'<div id="'+data.id+'" class="cc ' +
//data.type +
//'">\
//<div class="cc-img-main"></div>\
//<div class="cc-num">' +
//data.number +
//'</div>\
//<div class="cc-date">Valid Thru: ' +
//data.month +
//"/" +
//data.year +
//"</div>\
//</div>"
//)	
//}

//load trams data 
var load = function(data) {  
	clearTrans();
	balance = 0;
	startAppend(data);
//	balanceCalc(balance);  
	noTrans();
};

//count valid cc numbers
//var countValid = function(ccType) {    
//if(ccType == 'amex') {
//if($('#ccnum').val().length != 15) {
//$('#ccnum').removeClass('valid-green')
//$('#ccnum').addClass('valid-red')
//return false;
//} else {
//$('#ccnum').addClass('valid-green')
//$('#ccnum').removeClass('valid-red')
//return true;
//}
//} else {
//if($('#ccnum').val().length != 16) {
//$('#ccnum').addClass('valid-red')
//$('#ccnum').removeClass('valid-green')
//return false;
//} else {
//$('#ccnum').addClass('valid-green')
//$('#ccnum').removeClass('valid-red')
//return true;
//}
//}  
//}

//append trans data
function startAppend(data) {
	for (var i = 0; i < data.length; i++) {
		$(".trans-list").append(
				'<div class="trans-each">\
				<div class="trans-details">\
				<span class="action"></span>\
				<h3 class="trans-title">' +
				data[i].title +
				'</h3><div class="row">\
				<h5 class="trans-descript">' +
				data[i].descript + '</h5>\
				<h5 class="trans-cat"></h5>\
				<h5 class="trans-planDate">'+data[i].planDate+
				'</h5></div>\
				</div>\
				<div class="trans-price">\
				<h4 class="trans-price">\
				</h4>\
				</div>\
				</div><hr>'
		).show('fast');
		noTrans();
	}    
	detachAddBtn();
}

//calc balance and format
/*var balanceCalc = function balanceCalc(balance) {
	return balance < 0  ? $("#balance").html("$(" + Math.abs(balance).toLocaleString() + ")")  : $("#balance").html("$" + balance.toLocaleString() + "");
}*/

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
	var title = $(".cc:eq(0)").find('div.cc-num').text();
	$("#planTitle").text(title);
	var data = getUserPlan(planNo);
	load(data) ;

	$(document).on("click","#btn-save",function(){
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

//각각 영역 클릭시 상세정보 입력 모달 
$(document).on("click",".trans-each",function(){
	var modalData;
	var title = $(this).find('h3').text();
	var descript = $(this).find('h5.trans-descript').text();
	var cat = $(this).find('h5.trans-cat').text();
	if(cat != "") {
		switch(cat){
		case "관광" : $("input[value='관광']").attr("checked"); break;
		case "숙박" : $("input[value='숙박']").attr("checked"); break;
		case "식비" : $("input[value='식비']").attr("checked"); break;
		case "교통" : $("input[value='교통']").attr("checked"); break;
		case "기타" : $("input[value='기타']").attr("checked"); break;
		}
	}
	$("#title").text(title);
	$("#info").val(descript);
	$(".modal").show();
});

//click listener for active card
$(document).on("click", ".cc", function(e) {
	$(".cc").removeClass("cc-active");
	$(this).addClass("cc-active");
	var planNo = $(this).find('input').val();
	var title = $(this).find('div.cc-num').text();
	$("#planTitle").text(title);
	var data = getUserPlan(planNo);
	load(data);
});

//모달에서 수입 버튼 클릭 시 
$(document).on("click","#income",function(){
	$(".trans-each").each(function(){
		if($(this).find("h3").text() == $("#title").text()){
			$(this).find('h5.trans-descript').text($("#info").val());
			$(this).find('h5.trans-cat').text($("input[name='cat']:checked").val());
			$(this).find('h4.trans-price').text($("#price").val()+"\\");
			$(this).find('h4.trans-price').css("color", "rgb(113, 207, 66)");
			$(this).find('h4.trans-price').addClass("income");
			if(checkModal()){
				clearModal();
				$('.modal').hide();
			}else  alert("빈칸을 채워주세요.");
		}
	});
});

//모달 지출 버튼 클릭 시 
$(document).on("click","#expend",function(){
	$(".trans-each").each(function(){
		if($(this).find("h3").text() == $("#title").text()){
			$(this).find('h5.trans-descript').text($("#info").val());
			$(this).find('h5.trans-cat').text($("input[name='cat']:checked").val());
			$(this).find('h4.trans-price').text($("#price").val()+"\\");
			$(this).find('h4.trans-price').css("color", "#ff3232");
			$(this).find('h4.trans-price').addClass("expend");
			if(checkModal()){
				clearModal();
				$('.modal').hide();
			}else  alert("빈칸을 채워주세요.");
		}
	});
});

//새로운 계획 추가에서 수입 클릭 시 
$(document).on("click","#add-income",function(){
	var title = $("#add-title").val();
	var descript = $("#add-info").val();
	var planDate = $("#add-date").val();
	var cat = $("input[name='add-cat']:checked").val();
	var price = $("#add-price").val();
	if(!checkAddModal()) {
		alert("빈칸을 채워주세요.")
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
		alert("빈칸을 채워주세요.");
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


function getUserPlan(planNo){
	var result = new Array();
	$.ajax({
		url : "/budget/load/userplan",
		async : false,
		dataType: "json",
		type : "get",
		data : {"planNo" : planNo},
		success : function(data) {
			$.each(data,function(i,item){
				var jsondata = {}
				jsondata.title = data[i].title;
				jsondata.planDate = data[i].planDate;
				jsondata.descript = data[i].descript;
				result.push(jsondata); 
			});
		},
		error : function(error){
			alert("데이터 로딩 x")
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
			trans.exepnd = $(this).find('h4.expend').text().split("\\")[0];
		} else {
			trans.income = 0;
			trans.expend = 0;
		}

		if($(this).find('h5.trans-descript').text()=="null") trans.descript="";
		else trans.descript = $(this).find('h5.trans-descript').text();
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
	if($('#add-title').val("")==""||$('#add-date').val("")==""||$('#add-price').val("")==""||
			$("#add-info").val("")==""){
		return false;
	}
	return true;
}
function checkModal() {
	if($('#price').val("")==""||$("#info").val("")==""){
		return false;
	}
	return true;
}
//click listener for active card in modal selector
//$(document).on("click", ".cc-img", function(e) {
//$(".cc-img").removeClass("cc-md-active");
//$(this).addClass("cc-md-active");
//($(".md-cc > .cc-md-active").attr("class").split(" ")[1] == 'amex') ? $('#ccnum').attr('placeholder','**** ****** *****') : $('#ccnum').attr('placeholder','**** **** **** ****')   
//$('#ccnum, #year, #month').removeAttr('disabled');
//});




//add new card logic
//TODO: Add numbous checks, sanitize, error catching
//$(document).on("click", ".modal-add-cc", function(e) {
//var ccType = null;
//if ($(".md-cc > .cc-md-active").attr("class")) {
//ccType = $(".md-cc > .cc-md-active").attr("class").split(" ")[1];
//}
//var ccNum = $("#ccnum").val().substr($("#ccnum").val().length - 4);
//var month = $("#month").val();
//var year = $("#year").val();

//if (ccNum && month && year && ccType) {
//$(".modal").hide();
//$(".cc").removeClass("cc-active");

//ccModalAppend(ccType,ccNum,month,year)
//var p = data[data.length-1].id + 1
//data.push({
//type: ccType,
//number: (ccType == 'amex') ? "**** ****** "+ccNum : "**** **** **** "+ccNum ,
//month: month,
//year: year,
//id: p,
//transactions: []
//})
//clearTrans()
//clearModal();
//load(ccType,data)

//} else {
//alert("Sorry, missing required fields"); // will do for now
//}
//});

//keypress cc count
//$('#ccnum').on('keyup', function(){
//var ccType = $(".md-cc > .cc-md-active").attr("class").split(" ")[1];
//countValid(ccType);
//})
