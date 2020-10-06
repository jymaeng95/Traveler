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
	  $("#valarchive").on("change",function(e){
		  var selected = $("#valarchive option:selected").val();
		  location.href = "/mypage/message?pageNum=1&tabPage=archive&arcPage=" + selected;
			  });
	  
	  $('#deleteModal').on('show.bs.modal', function (event) { // myModal 윈도우가 오픈할때 아래의 옵션을 적용
			  var button = $(event.relatedTarget); // 모달 윈도우를 오픈하는 버튼
			  var titleTxt = button.data('title'); // 버튼에서 data-title 값을 titleTxt 변수에 저장
			  var type = button.data('type');
			  var id = button.data('id');
			  $('#modal-title').html(titleTxt); // 모달위도우에서 .modal-title을 찾아 titleTxt 값을 치환
			  $('#modal_type').val(type);
			  $('#modal_id').val(id);
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
	var url = "/mypage/popup_send?rcver=no";
	var name = "popup";
	window.open(url,name,"width=500, height=500, resizable=yes ");
}

function popup(mid, readType){	
	
	var url = "/mypage/popup_more?mid="+mid+"&readType="+readType;
	var name = "popup2";
	window.open(url,name,"width=500, height=500, resizable=yes ");
}

function updateMsg2(targetId,mid_rcv,readstatus) {
	
	$.ajax({
		url : "/mypage/message/update2",
		type : "post",
		dataType : "json",
		data : {
			"targetId_rcv" : targetId,
			"mid_rcv" : mid_rcv,
			"readstatus_rcv" : readstatus
		},
		success : function(data) {
			location.reload();
			alert("update");
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
	if( $("#selectAll4").is(':checked') ){
        $('input[name=options5]').prop("checked", true);
      }else{
        $('input[name=options5]').prop("checked", false);
      }

}

//상단 선택버튼 클릭시 체크된 Row의 값을 가져온다.
function delselect(type){ 
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
		
		delMsg2(userid,no,type);
	});
}

function updateMsg(targetId,mid,status, type) {
	if(type == 'rcv'){
		$.ajax({
			url : "/mypage/message/update",
			type : "post",
			dataType : "json",
			data : {
				"targetId_rcv" : targetId,
				"mid_rcv" : mid,
				"status_rcv" : status,
				"type" : type
			},
			success : function(data) {
				location.reload();
			},
			error : function(error) {
				alert("update error");
			}
		});
	}
	else {
		$.ajax({
			url : "/mypage/message/update",
			type : "post",
			dataType : "json",
			data : {
				"targetId_send" : targetId,
				"mid_send" : mid,
				"status_send" : status,
				"type" : type
			},
			success : function(data) {
				location.reload();
			},
			error : function(error) {
				alert("update error");
			}
		});
	}
}



//개별 삭제
function delMsg() {
		if($('#modal_type').val() == 'rcv'){
			$.ajax({
				url : "/mypage/message/delete",
				type : "post",
				dataType : "json",
				data : {
					"targetId_rcv" : $('#modal_id').val(),
					"mid_rcv" : $('#modal-title').html(),
					"type" : $('#modal_type').val()
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
		else {
			$.ajax({
				url : "/mypage/message/delete",
				type : "post",
				dataType : "json",
				data : {
					"targetId_send" : $('#modal_id').val(),
					"mid_send" : $('#modal-title').html(),
					"type" : $('#modal_type').val()
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
	}
//선택삭제
function delMsg2(targetId, mid, type) {
		if(type == 'rcv' || type == 'rcv_arc') {
			$.ajax({
				url : "/mypage/message/delete",
				type : "post",
				dataType : "json",
				data : {
					"targetId_rcv" : targetId,
					"mid_rcv" : mid,
					"type" : type
				},
				success : function(data) {
					location.reload();
				},
				error : function(error) {
					alert("delete error");
				}
			});
		}
		else {
			$.ajax({
				url : "/mypage/message/delete",
				type : "post",
				dataType : "json",
				data : {
					"targetId_send" : targetId,
					"mid_send" : mid,
					"type" : type
				},
				success : function(data) {
					location.reload();
				},
				error : function(error) {
					alert("delete error");
				}
			});
		}
	}
