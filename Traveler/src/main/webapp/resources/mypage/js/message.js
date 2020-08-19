$(document).ready(function() {
	formatDate();

	  //paging
	  var actionForm = $("#actionForm");
	  $(".paginate_button a").on("click",function(e){
		e.preventDefault();
		console.log('click');
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
		  });
	  $(".tab a").on("click",function(e){
			e.preventDefault();
			console.log('click');
			actionForm.find("input[name='tabPage']").val($(this).attr("href"));
			actionForm.submit();
			  });
	  $("#valarchive").on("change",function(e){
		  var selected = $("#valarchive option:selected").val();
		  location.href = "/mypage/message?pageNum=1&tabPage=archive&arcPage=" + selected;
			  });
	  
});

//날짜 변환 함수
function formatDate(){
	var curDate = $("[id^=date]");
	curDate.each(function(i) {
		var cdate = $("#date"+i).html();
		var today, resultDate;
		today = new Date();
		resultDate = new Date(cdate);
		timegap = (today - resultDate)/(60*60*1000);
		
		var curYear = resultDate.getFullYear();
		var curMonth = (resultDate.getMonth() + 1);
		var curDay = resultDate.getDate();
	
		if (timegap <= 24) {
			if (Math.floor(timegap) == 0) {
				resultDate = Math.floor(timegap * 24) + '분 전';
			}
			else {
				resultDate = Math.floor(timegap) + '시간 전';
			}
		}
		else {
			resultDate = curYear + '-' + curMonth + '-' + curDay;
		}
		$("#date"+i).html(resultDate);
		
	});
}

function sendpop(){	
	var url = "/mypage/popup_send";
	var name = "popup";
	window.open(url,name,"width=500, height=500, resizable=yes ");
}

function popup(){	
	
	var url = "/mypage/popup_more";
	var name = "popup2";
	window.open(url,name,"width=500, height=500, resizable=yes ");
}
function popup2(){	
	
	var url = "/mypage/popup_more2";
	var name = "popup3";
	window.open(url,name,"width=500, height=500, resizable=yes ");
}

function updateMsg2(userId,mid,readstatus) {
	
	$.ajax({
		url : "/mypage/message/update2",
		type : "post",
		dataType : "json",
		data : {
			"userId" : userId,
			"mid" : mid,
			"readstatus" : readstatus
		},
		success : function(data) {
			popup();
			location.reload();
		},
		error : function(error) {
			alert("update error");
		}
	});
}


/*테이블 js*/

function selectAll(){

	if( $("#selectAll").is(':checked') ){
        $('table tbody input[type="checkbox"]').prop("checked", true);
      }else{
        $('table tbody input[type="checkbox"]').prop("checked", false);
      }
	if( $("#selectAll1").is(':checked') ){
        $('input[name=options1]').prop("checked", true);
      }else{
        $('input[name=options1]').prop("checked", false);
      }
	if( $("#selectAll2").is(':checked') ){
        $('input[name=options2]').prop("checked", true);
      }else{
        $('input[name=options2]').prop("checked", false);
      }
	if( $("#selectAll3").is(':checked') ){
        $('input[name=options4]').prop("checked", true);
      }else{
        $('input[name=options4]').prop("checked", false);
      }

}

//상단 선택버튼 클릭시 체크된 Row의 값을 가져온다.
function delselect(){ 
	var rowData = new Array();
	var tdArr = new Array();
	var checkbox = $('table tbody input[type="checkbox"]:checked');
	
	// 체크된 체크박스 값을 가져온다
	checkbox.each(function(i) {

		// checkbox.parent() : checkbox의 부모는 <td>이다.
		// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
		var tr = checkbox.parent().parent().eq(i);
		var td = tr.children();
		
		// 체크된 row의 모든 값을 배열에 담는다.
		rowData.push(tr.text());
		
		// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
		var no = td.eq(1).text();
		var userid = td.eq(2).text();
		
		console.log("no : " + no);
		console.log("userid : " + userid);
		
		delMsg2(userid,no);
	});
}

$('#deleteModal').on('show.bs.modal', function (event) { // myModal 윈도우가 오픈할때 아래의 옵션을 적용
	  var button = $(event.relatedTarget) // 모달 윈도우를 오픈하는 버튼
	  var titleTxt = button.data('title') // 버튼에서 data-title 값을 titleTxt 변수에 저장
	  var modal = $(this)
	  modal.find('.modal-title').val(titleTxt) // 모달위도우에서 .modal-title을 찾아 titleTxt 값을 치환
	});

function updateMsg(userId,mid,status) {
		
		$.ajax({
			url : "/mypage/message/update",
			type : "post",
			dataType : "json",
			data : {
				"userId" : userId,
				"mid" : mid,
				"status" : status
			},
			success : function(data) {
				location.reload();
			},
			error : function(error) {
				alert("update error");
			}
		});
	}



//그냥 삭제
function delMsg(userId) {
		
		$.ajax({
			url : "/mypage/message/delete",
			type : "post",
			dataType : "json",
			data : {
				"userId" : userId,
				"mid" : $('#myModalLabel').val()
			},
			success : function(data) {
				alert("쪽지를 삭제하였습니다.");
				location.reload();
			},
			error : function(error) {
				alert("delete error");
			}
		});
	}
//선택삭제
function delMsg2(userId, mid) {
		
		$.ajax({
			url : "/mypage/message/delete",
			type : "post",
			dataType : "json",
			data : {
				"userId" : userId,
				"mid" : mid
			},
			success : function(data) {
				location.reload();
			},
			error : function(error) {
				alert("delete error");
			}
		});
	}
