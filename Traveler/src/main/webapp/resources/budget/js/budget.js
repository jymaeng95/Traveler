/* Josh Channin
   Fake data from generatedata.com
*/
// vars
var balance = 0;

//// append cc to modal
//var ccModalAppend= function(ccType, ccNum, month, year) {
//	if(ccType == 'amex') {	
//		$(".cc-select").prepend(
//		  '<div class="cc ' +
//		  ccType +
//		  ' cc-active">\
//		  <div class="cc-img-main"></div>\
//		  <div class="cc-num">**** *******  ' +
//		  ccNum +
//		  '</div>\
//		  <div class="cc-date">Valid Thru: ' +
//		  month +
//		  "/" +
//		  year +
//		  "</div>\
//		  </div>"
//		);
//	} else {
//		$(".cc-select").prepend(
//		  '<div class="cc ' +
//		  ccType +
//		  ' cc-active">\
//		  <div class="cc-img-main"></div>\
//		  <div class="cc-num">**** **** **** ' +
//		  ccNum +
//		  '</div>\
//		  <div class="cc-date">Valid Thru: ' +
//		  month +
//		  "/" +
//		  year +
//		  "</div>\
//		  </div>"
//		);
//	}	
//}


// append credit cards
//var ccAppend = function(data) {
//	$(".cc-select").append(
//      '<div id="'+data.id+'" class="cc ' +
//      data.type +
//      '">\
//      <div class="cc-img-main"></div>\
//      <div class="cc-num">' +
//      data.number +
//      '</div>\
//      <div class="cc-date">Valid Thru: ' +
//      data.month +
//      "/" +
//      data.year +
//      "</div>\
//      </div>"
//    )	
//}

// load trams data 
var load = function(data) {  
      clearTrans();
      balance = 0;
      startAppend(data);
      balanceCalc(balance);  
      noTrans();
};

// count valid cc numbers
//var countValid = function(ccType) {    
//    if(ccType == 'amex') {
//      if($('#ccnum').val().length != 15) {
//        $('#ccnum').removeClass('valid-green')
//        $('#ccnum').addClass('valid-red')
//        return false;
//      } else {
//        $('#ccnum').addClass('valid-green')
//        $('#ccnum').removeClass('valid-red')
//        return true;
//      }
//    } else {
//       if($('#ccnum').val().length != 16) {
//        $('#ccnum').addClass('valid-red')
//        $('#ccnum').removeClass('valid-green')
//        return false;
//      } else {
//        $('#ccnum').addClass('valid-green')
//        $('#ccnum').removeClass('valid-red')
//        return true;
//      }
//    }  
//}

// append trans data
function startAppend(data) {
    for (var i = 0; i < data.length; i++) {
//        if (e.transactions[i].action == "credit") {
//          var action = "trans-plus";
//          var color = "green";
//          balance = balance + parseFloat(e.transactions[i].amount,10);
//        } else {
//          var action = "trans-minus";
//          var color = "blue";
//          balance = balance - parseFloat(e.transactions[i].amount,10);
//        }
        $(".trans-list").append(
          '<div class="trans trans">\
           <div class="trans-details">\
          <span class="action"></span>\
          <h3 class="trans-name">' +
          data[i].title +
          '</h3>\
          <h5 class="trans-type-date">' +
          data[i].descript +
          " - " +data[i].planDate+
		 '</h5>\
          </div>\
		  <div class="trans-amt">\
		  <h4 class="trans-amt">\
		  </h4>\
          </div>\
          </div><hr>'
        ).show('fast');
      noTrans();
    }    
}

// calc balance and format
var balanceCalc = function balanceCalc(balance) {
     return balance < 0  ? $("#balance").html("$(" + Math.abs(balance).toLocaleString() + ")")  : $("#balance").html("$" + balance.toLocaleString() + "");
}

// clear trans list
var clearTrans = function(){
	$(".trans-list").empty().hide();
}

// clear modal
var clearModal = function() {
  $('.cc-img').removeClass('cc-md-active')
  $('#ccnum, #year, #month').val('')
  $('#ccnum').css('border','1px solid #e1e1e1;')
  $('#ccnum').removeClass('valid-red').removeClass('valid-green')
}

// no trans
var noTrans = function() {
    $('.trans-list').children().length == 0 ? $(".trans-list").append('<h5 class="no-trans"> No transactions for this card</h5>').show('fast') :"";
}

// load inital data
$(document).ready(function() {    

  $(".cc:eq(0)").addClass("cc-active")  
  var planNo = $(".cc:eq(0)").find('input').val();
  var data = getUserPlan(planNo);
  load(data)  
//open modal
  $(".modal-open").on("click", function(e) {
    $(".modal").show();
  });

});

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
				jsondata.planDay = data[i].planDay;
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


// click listener for active card
$(document).on("click", ".cc", function(e) {
  $(".cc").removeClass("cc-active");
  $(this).addClass("cc-active");
  var planNo = $(this).find('input').val();
  var data = getUserPlan(planNo);
  load(data)  
});

// click listener for active card in modal selector
//$(document).on("click", ".cc-img", function(e) {
//  $(".cc-img").removeClass("cc-md-active");
//  $(this).addClass("cc-md-active");
//  ($(".md-cc > .cc-md-active").attr("class").split(" ")[1] == 'amex') ? $('#ccnum').attr('placeholder','**** ****** *****') : $('#ccnum').attr('placeholder','**** **** **** ****')   
//  $('#ccnum, #year, #month').removeAttr('disabled');
//});

// close modal
$(document).on("click", ".modal-close", function(e) {
  $(".modal").hide();
  clearModal();
});


// add new card logic
// TODO: Add numbous checks, sanitize, error catching
//$(document).on("click", ".modal-add-cc", function(e) {
//  var ccType = null;
//  if ($(".md-cc > .cc-md-active").attr("class")) {
//    ccType = $(".md-cc > .cc-md-active").attr("class").split(" ")[1];
//  }
//  var ccNum = $("#ccnum").val().substr($("#ccnum").val().length - 4);
//  var month = $("#month").val();
//  var year = $("#year").val();
//
//  if (ccNum && month && year && ccType) {
//    $(".modal").hide();
//    $(".cc").removeClass("cc-active");
//	
//	  ccModalAppend(ccType,ccNum,month,year)
//    var p = data[data.length-1].id + 1
//    data.push({
//      type: ccType,
//      number: (ccType == 'amex') ? "**** ****** "+ccNum : "**** **** **** "+ccNum ,
//      month: month,
//      year: year,
//      id: p,
//      transactions: []
//    })
//    clearTrans()
//    clearModal();
//    load(ccType,data)
//	
//  } else {
//    alert("Sorry, missing required fields"); // will do for now
//  }
//});

// keypress cc count
//$('#ccnum').on('keyup', function(){
//  var ccType = $(".md-cc > .cc-md-active").attr("class").split(" ")[1];
//  countValid(ccType);
//})
