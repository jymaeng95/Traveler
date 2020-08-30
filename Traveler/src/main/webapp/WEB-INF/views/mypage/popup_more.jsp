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
function popup(){	
	var url = "/mypage/message2";
	var name = "popup2";
	window.open(url,name,"width=500, height=500, resizable=yes ");
}
</script>
<body>
	<c:if test="${readType eq 'rcv'}"><h4>받은 쪽지</h4><hr/>
	<div>
        <label>보낸 사람   <c:out value="${msg.sender }"/></label>
    </div>
    <div>
        <label>받은 시간   <c:out value="${msg.senddate }"/></label>
    </div>
    <div class="form-group">
    	<label for="message">내용</label>
    	<textarea class="form-control" name="content" id="content" rows="15" cols="30" placeholder="${msg.mcontent }" disabled></textarea>
    </div>
    
    <input type="submit" value="답장하기" class="btn btn-primary btn-block" onclick="popup()"/></c:if>
    
    <c:if test="${readType eq 'send' }">
    <h4>보낸 쪽지</h4><hr/>
	<div>
        <label>받는 사람   <c:out value="${msg.userId }"/></label>
    </div>
    <div>
        <label>보낸 시간   <c:out value="${msg.senddate }"/></label>
    </div>
    <div class="form-group">
    	<label for="message">내용</label>
    	<textarea class="form-control" name="content" id="content" rows="15" cols="30" placeholder="${msg.mcontent }" disabled></textarea>
    </div>
    </c:if>

<!-- <pre id="output"></pre> -->

</body>
</html>