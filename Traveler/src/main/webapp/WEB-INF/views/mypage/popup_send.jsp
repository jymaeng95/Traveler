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
		 		dataType : "text",
		 		data : {
			 		"sender_send" : sender,
		 			"targetId_send" : $('input[name=userId]').val(),
		 			"mcontent_send" : $('#mcontent').val(),
			 		"sender_rcv" : sender,
		 			"targetId_rcv" : $('input[name=userId]').val(),
		 			"mcontent_rcv" : $('#mcontent').val(),
		 			"userId" : $('input[name=userId]').val()
		 		},
		 		success : function(data) {
			 		if(data == "right"){
			 			alert("쪽지를 보냈습니다.");
			 			window.close();
			 		}
			 		else alert(data);
		 		},
		 		error : function(error) {
		 			alert("error");
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
  <h4>쪽지 보내기</h4><hr/>
    <div class="form-group">
        <label for="name">보내는 사람</label>
        <input class="form-control" type="text" name="sender" id="sender" value="${userInfo.userId }" style="display:none;"/>
        <c:choose><c:when test="${rcver eq 'no'}"><input class="form-control" type="text" name="userId" id="userId"/></c:when>
        <c:otherwise><input class="form-control" type="text" name="userId" id="userId2" value ='${rcver }' disabled/></c:otherwise></c:choose>
        
    </div>
    
    <div class="form-group">
    	<label for="message">내용</label>
    	<textarea class="form-control" name="mcontent" id="mcontent" rows="15" cols="30" onkeyup="count(this.value)"></textarea>
    	<span style="color:#aaa;" id="counter">(0 / 최대 200자)</span>
    </div>
    
    <p>
    	<input type="submit" value="보내기" class="btn btn-primary btn-block" onclick="sendMsg('${userInfo.userId}')"/>
    </p>
  </body>
</html>