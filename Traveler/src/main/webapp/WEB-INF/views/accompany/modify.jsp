<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<%@ include file="../includes/sidebar_setting.jsp"%>
<style>
body {
  margin: 2em auto;
  max-width: 600px;
}

form div {
    margin-bottom: 0.5em;
}
form div label, form div input {
    display: block;
    margin-bottom: 0.3em;
}

</style>
</head>

<script type="text/javascript">
$(document).ready(function() {
	var strDate1 = $('.startdate').html();
	var date = new Date(strDate1);
	var totaldate = $('#totaldate').html();
	totaldate *= 1;
	$('.date').html(strDate1 + " -> " +
			date.getFullYear() + "-" + (date.getMonth()+1) + "-" + (date.getDate() + totaldate-1));
});

function modifyAcc(contentId, planNo, writer) {
	if($("#selectplan option:selected").val() < $('#cnt').val())
		alert("이미 신청한 인원이 더 많습니다.");
	else{
		$.ajax({
			url : "/accompany/update",
			type : "post",
			dataType : "json",
			data : {
				"contentId" : contentId,
				"content" : $("#content").val(),
				"title" : $("#title").val(),
				"numperson" : $("#selectplan option:selected").val()
			},
			success : function(data) {
				alert("게시글이 수정되었습니다.");
				location.href = "/accompany/board_detail?contentId="+contentId+"&planNo="+planNo+"&writer="+writer;
			},
			error : function(error) {
				alert("update error");
			}
		});
	}
}

function submit(){
	if($("#title").val()=="" || $("#content").val()=="") {
		alert("내용을 모두 입력해주세요");
	} else {
		location.href="/accompany/board";
		alert("게시글이 등록되었습니다");
	}
}

</script>

<body>

	<div>
        <label>제목</label>
        <input type="text" name="title" id="title" />
    </div>
    <div>
        <hr/><label>가는 날짜</label>
 		<p class="startdate" style="display:none;">${startdate}</p>
 		<p class="date">
    </div>    
    <div>
        <hr/><label>가는곳</label>
 		<c:forEach items="${u_list}" var="list" varStatus="status">
 		<p id="totaldate" style="display:none;">${list.planTotalDate }</p>
 		<p>${list.title }</p>
 		</c:forEach>
    </div>
    <div>
    <hr/><p>모집 인원</p>
    <input type="hidden" id="cnt" value="${cnt }"/>
    <select id="selectplan">
    	<c:forEach var="i" begin="1" end="10">
			<option>${i }</option>
		</c:forEach>
	</select>
    </div>
    <div class="form-group">
    	<label for="message">내용</label>
    	<textarea class="form-control" name="content" id="content" rows="15" cols="30"></textarea>
    </div>

    <input value="${list.userId }" style="display:none;" id="userId">        

	<button id="submit" class="btn btn-primary btn-lg" onclick="modifyAcc('${bno }','${planNo }','${userInfo.userId }');">제출</button>

</body>
</html>