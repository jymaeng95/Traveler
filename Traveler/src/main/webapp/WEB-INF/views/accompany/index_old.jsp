<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../includes/sidebar_setting.jsp" %>
<style>
h2{
  font-size: 3rem;
  margin: 0 0 2rem;
  line-height: 1;
}
.container{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 10rem auto;
}
.fb-style-btn{
  border-radius: 2px;
  font-size: 1rem;
  font-weight: bold;
  padding: 1em 2em;
  margin: 0 3em;
  outline: none;
}
.fb-style-dark{
  box-shadow: inset 0 1px 0 0 #4d73bf;
  background-color: #4267b2;
  border: solid 1px #4267b2;
  color: white;
  text-shadow: 0 1px 0 #3359a5;
}
.fb-style-dark:hover{
  background-color: #2b54a7;
  cursor: pointer;
}
.fb-style-dark:active{
  background-color: #1d4698;
  border-color: #1d4698;
}
.fb-style-light{
  background-color: #f6f7f9;
  border: solid 1px #3ced0d4;
  color: #4b4f56;
}
.fb-style-light:hover{
  background-color: #e9ebee;
  cursor: pointer;
}
.fb-style-light:active{
  background-color: #d8dade;
  border-color: #d8dade;
}
</style>
</head>
<script>
$(document).ready(function(){
	$("#btn1").click(function(){
		if($("#userId").val() == "") {
			alert("로그인하세요")
			location.href = "/";
		}else {
			check2();
		}
	});
	$("#confirm").click(function(){
		//var selected = $("#selectplan option:selected").val();
		//location.href = "/accompany/write?planNo=" + selected;
		check();
	});
});

function check(){
	var selected = $("#selectplan option:selected").val();
	$.ajax({
		url : "/accompany/check",
		type : "post",
	 	dataType : "text",
	 	data : {
	 		"planNo" : selected
 		},
 		success : function(data) {
 			if(data == "이미 모집중인 플랜입니다.") alert(data);
 			else location.href = "/accompany/write?planNo=" + selected;
 		},
 		error : function(error) {
	 		alert("error.");
 		}
	});
}

function check2(){
	$.ajax({
		url : "/accompany/check2",
		type : "post",
	 	dataType : "text",
	 	data : {
	 		"userId" : $('#userId').val()
 		},
 		success : function(data) {
 			if(data == "먼저 여행 플랜을 만드세요.") {
 	 			alert(data);
 	 			location.href="/plan/plan";
 			}
 			else $("#myModal").modal("show");
 		},
 		error : function(error) {
	 		alert("error.");
 		}
	});
}
</script>
<body>
	<%@include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<article class="container">
		  <h2>동행</h2>
		  <h4>같이 여행하고 싶은 동행자를 찾아보세요.</h4><br/>
		  <section>
		    <button type="button" name="button" id="btn1" class="fb-style-btn fb-style-dark" data-toggle="modal" data-target="#myModal">
		         직접 모집</button>
		    <button type="button" name="button" id="btn2" class="fb-style-btn fb-style-light" onclick="location.href='/accompany/board'">
		         동행 검색</button>
		  </section>
		</article>
	</main>
	
	<!-- 모달 -->
	<div class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" id="myModal" aria-hidden="true">
		<div class="modal-dialog"
			style="max-width: 100%; width: auto; display: table;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">동행할 여행 선택</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<form id="plan_info" method="get" action="/plan/create">
					<div class="modal-body">
						<select id="selectplan" style="width:350px">
						<c:forEach items="${p_list}" var="list" varStatus="status">
							<option value="${list.planNo }">${list.planTitle }</option>
						</c:forEach>
						</select>
					</div>
				</form>
				<div class="modal-footer">
					<button class="btn btn-primary" type="button" id="confirm">예</button>
					<button class="btn btn-primary" type="button" data-dismiss="modal">아니요</button>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="userId" value="${userInfo.userId }"/>
<script src="/resources/index/js/holder.js"></script>
</body>
</html> --%>