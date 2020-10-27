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
<script>
function popup(rcver){   
   var url = "/mypage/popup_send?rcver="+rcver;
   var name = "popup2";
   window.open(url,name,"width=500, height=500, resizable=yes ");
}

function accept(rcver){   
   var url = "/mypage/popup_send?rcver="+rcver;
   var name = "popup2";
   window.open(url,name,"width=500, height=500, resizable=yes ");
}

function updateGrp(planNo, guestId, hostId) {

	   
	   var noArray = planNo.split(",");
	   
	   console.log("planno "+noArray[0]);
	   console.log("bno "+noArray[1]);

	   $.ajax({
	      url : "/accompany/guest/insert",
	      type : "post",
	      dataType : "text",
	      data : {
	         "accBno" : noArray[1],
	         "planNo" : noArray[0],
	         "hostId" : hostId,
	         "guestId" : guestId,
	         "title" : $('#content_acc').val()
	      },
	      success : function(data) {
	          alert(data);
	         if(data == "수락했습니다.") window.close();  
	      },
	      error : function(error) {
	         alert("error");
	      }
	   });
	}
</script>
<body>
	<c:choose>
		<c:when test="${readType eq 'rcv'}">
			<h4>받은 쪽지</h4>
			<hr />
			<div>
				<label>보낸 사람 <c:out value="${msg.sender_rcv }" /></label>
				<p id="sender" style="display: none;">${msg.sender_rcv }</p>
				<p id="userId" style="display: none;">${userInfo.userId }</p>
			</div>
			<div>
				<label>받은 시간 <c:out value="${msg.senddate_rcv }" /></label>
			</div>
			<div class="form-group">
				<label for="message">내용</label>
				<textarea class="form-control" name="content" id="content" rows="15"
					cols="30" placeholder="${msg.mcontent_rcv }" disabled></textarea>
			</div>
			<input type="submit" value="답장하기" class="btn btn-primary btn-block"
				onclick="popup('${msg.sender_rcv}')" />
		</c:when>



		<c:when test="${readType eq 'accom'}">
			<h4>받은 쪽지</h4>
			<hr />
			<div>
				<label>보낸 사람 <c:out value="${msg.sender_rcv }" /></label>
				<p id="sender" style="display: none;">${msg.sender_rcv }</p>
				<p id="userId" style="display: none;">${userInfo.userId }</p>
			</div>
			<div>
				<label>받은 시간 <c:out value="${msg.senddate_rcv }" /></label>
			</div>
			<div class="form-group">
				<label for="message">내용</label>
				<textarea class="form-control" name="content" id="content_acc"
					rows="15" cols="30" readonly>${msg.mcontent_rcv }</textarea>
			</div>
			<p id="contentId" style="display: none;">${msg.bno_rcv }</p>
			<input type="submit" value="수락하기" class="btn btn-primary btn-block"
				onclick="updateGrp('${msg.bno_rcv}', '${msg.sender_rcv }', '${userInfo.userId }');" />
		</c:when>


		<c:otherwise>
			<h4>보낸 쪽지</h4>
			<hr />
			<div>
				<label>받는 사람 <c:out value="${msg.targetId_send }" /></label>
			</div>
			<div>
				<label>보낸 시간 <c:out value="${msg.senddate_send }" /></label>
			</div>
			<div class="form-group">
				<label for="message">내용</label>
				<textarea class="form-control" name="content" id="content" rows="15"
					cols="30" placeholder="${msg.mcontent_send }" disabled></textarea>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>