$(document).ready(function(){

	var sigungu = $("#sigunguCode").val();
	var contentType = $("#contentTypeId").val();
	var pNum = $('input[name=pageNum]').val()
	var allData = {"pageNo" : pNum ,"sigunguCode" : sigungu, "contentTypeId" : contentType , "numOfRow" : 10};
	$.ajax({
		url : "/spot/information",
		type : "get",
		dataType : "json",
		data : allData,
		success : function(data) {
			$.each(data, function(key, val){
				//alert(val[key]);
				$("#photo"+key).attr("style","background-image : url("+data[key].firstImage2+")");
				$("#title"+key).text(data[key].title);
				$("#addr"+key).text(data[key].addr1);
				$("#overview"+key).html(data[key].overview);
				$("#btn-detail"+key).attr("onclick","imgClick("+data[key].contentId+","+data[key].contentTypeId+");");
				$(".totalCount").text(data[key].totalCount);
			});
			if(sigungu=="" && contentType==""){
				$("input:checkbox[name='sigungu']:checkbox[value='']").prop('checked', true);
				$("input:checkbox[name='contentType']:checkbox[value='']").prop('checked', true);
			} else if(sigungu==""){
				$("input:checkbox[name='sigungu']:checkbox[value='']").prop('checked', true);
				$("input:checkbox[name='contentType']:checkbox[value="+contentType+"]").prop('checked', true);
			} else if(contentType==""){
				$("input:checkbox[name='sigungu']:checkbox[value="+sigungu+"]").prop('checked', true);
				$("input:checkbox[name='contentType']:checkbox[value='']").prop('checked', true);
			} else {
				$("input:checkbox[name='sigungu']:checkbox[value="+sigungu+"]").prop('checked', true);
				$("input:checkbox[name='contentType']:checkbox[value="+contentType+"]").prop('checked', true);
					
			}
		},
		error : function(error) {
			alert("첫페이지 에러");
		}
	});

	$('input[type="checkbox"][name="sigungu"]').click(function(){
		//클릭 이벤트 발생한 요소가 체크 상태인 경우
		if ($(this).prop('checked')) {
			//체크박스 그룹의 요소 전체를 체크 해제후 클릭한 요소 체크 상태지정
			$('input[type="checkbox"][name="sigungu"]').prop('checked', false);
			$(this).prop('checked', true);
		}
	});
	$('input[type="checkbox"][name="contentType"]').click(function(){
		//클릭 이벤트 발생한 요소가 체크 상태인 경우
		if ($(this).prop('checked')) {
			//체크박스 그룹의 요소 전체를 체크 해제후 클릭한 요소 체크 상태지정
			$('input[type="checkbox"][name="contentType"]').prop('checked', false);
			$(this).prop('checked', true);
		}
	});

	var actionForm = $("#actionForm");

	$(".paginate_button a").on("click", function(e) {
		e.preventDefault();
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});

});

function searchClick() {
	var form = document.srchForm;
	var sigungu = $("input[name='sigungu']:checked").val();
	var contentType = $("input[name='contentType']:checked").val();
	$("#s_Code").val(sigungu);
	$("#ctype_id").val(contentType);

	form.submit();
}
function imgClick(contentId,contentTypeId){
	$("#imgForm").append("<input type='hidden' name='contentId' value="+contentId+">");
	$("#imgForm").append("<input type='hidden' name='contentTypeId' value="+contentTypeId+">");
	$("#imgForm").attr("action","/spot/detail");
	$("#imgForm").submit();
}