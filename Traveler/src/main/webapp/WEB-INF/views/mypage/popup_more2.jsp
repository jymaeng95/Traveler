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
$(document).ready(function() {
    document.getElementById("name").value = $("#parentId",opener.document).val();
    document.getElementById("time").value = $("#parentId2",opener.document).val();
    document.getElementById("content").value = $("#parentId3",opener.document).val();
});

</script>
<body>
	<div>
        <label>받는 사람</label>
        <input type="text" name="name" id="name" disabled/>
    </div>
    <div>
        <label>보낸 시간</label>
        <input type="text" name="time" id="time" disabled/>
    </div>
    <div class="form-group">
    	<label for="message">내용</label>
    	<textarea class="form-control" name="content" id="content" rows="15" cols="30" disabled></textarea>
    </div>
    
</form>	
<pre id="output"></pre>

</body>
</html>