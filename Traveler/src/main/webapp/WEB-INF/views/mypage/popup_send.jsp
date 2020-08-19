<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
  <head>
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
	<script>
	$(document).ready(function() {
	    document.getElementById("userId").value = $("#name",opener.document).val();

	});
  
  function sendMsg(sender) {
	  if($('#sender').val() == $('#userId').val()){
		alert("자신에게 보낼 수 없습니다.");
	  } 
	  else {
		  if($('#mcontent').val().length > 200){alert("최대 200자까지 입력 가능합니다.");}
		  else{
		 	$.ajax({
		 		url : "/mypage/message/send",
		 		type : "post",
		 		dataType : "json",
		 		data : {
			 		"sender" : sender,
		 			"userId" : $('#userId').val(),
		 			"mcontent" : $('#mcontent').val()	
		 		},
		 		success : function(data) {
		 			alert("쪽지를 보냈습니다.");
		 			window.close();
		 		},
		 		error : function(error) {
		 			alert("존재하지 않는 아이디입니다.");
		 		}
		 	});
		  }
		}
	 }

//글자 체크
  function count(val){
 	
      $('#counter').html("("+val.length+" / 최대 200자)");

      if (val.length > 200){
          alert("최대 200자까지 입력 가능합니다.");
          $(this).val(content.substring(0, 200));
          $('#counter').html("(200 / 최대 200자)");
      }
  }	
  
  </script>
  <body>
    <div class="form-group">
        <label for="name">Name</label>
        <input class="form-control" type="text" name="sender" id="sender" value="${userInfo.userId }" style="display:none;"/>
        <input class="form-control" type="text" name="userId" id="userId" />
        
    </div>
    
    <div class="form-group">
    	<label for="message">Leave a message</label>
    	<textarea class="form-control" name="mcontent" id="mcontent" rows="15" cols="30" onkeyup="count(this.value)"></textarea>
    	<span style="color:#aaa;" id="counter">(0 / 최대 200자)</span>
    </div>
    
    <p>
        <button class="form-btn" type="button" id="btn-submit" onClick="sendMsg('${userInfo.userId}');">Send</button>
    </p>
  </body>
</html>