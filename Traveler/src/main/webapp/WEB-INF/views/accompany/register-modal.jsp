<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css"
	rel="stylesheet">
<link href="/resources/accompany/css/register-modal.css"
	rel="stylesheet">
<link href="/resources/util/css/font.css" rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.js"></script>
<script src="/resources/accompany/js/register-modal.js"></script>
<script src="https://kit.fontawesome.com/27324e8e3c.js"
	crossorigin="anonymous"></script>

</head>
<body>

	<table class="table"
		style="margin-bottom: 50px; background: gainsboro;">
		<!-- c태그로 DB에서 추천 가져와서 하기 -->
		<thead class="thead-dark">
			<tr>
				<th scope="col">#</th>
				<th scope="col">First</th>
				<th scope="col">Last</th>
				<th scope="col">Handle</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th scope="row">1</th>
				<td>Mark</td>
				<td>Otto</td>
				<td>@mdo</td>
				<td class="row-options"><i class="fas fa-search-plus"></i> <i
					class="fas fa-trash-alt"></i></td>
			</tr>
			<tr>
				<th scope="row">2</th>
				<td>Jacob</td>
				<td>Thornton</td>
				<td>@fat</td>
				<td class="row-options"><i class="fas fa-search-plus"></i> <i
					class="fas fa-trash-alt"></i></td>
			</tr>
			<tr>
				<th scope="row">3</th>
				<td>Larry</td>
				<td>the Bird</td>
				<td>@twitter</td>
				<td class="row-options"><i class="fas fa-search-plus"></i> <i
					class="fas fa-trash-alt"></i></td>
			</tr>
		</tbody>
	</table>


	<!-- 신청서 작성하기  -->
	<form id="contact" action="" method="post" >
		<h3>${title } 새로운 동행 </h3>
		
		<fieldset>
			<input placeholder="동행 제목을 적어주세요" type="text" tabindex="1"
				required>
		</fieldset>
		<fieldset>
			<select id="limitPerson" name="limitPerson" tabindex="2" required autofocus>
					<option value="" disabled selected>동행 신청 인원을 선택해주세요.</option>
						<c:forEach  var="i" begin="1" end="10">
							<option value="${i }"><c:out
									value="${i}"></c:out></option>
						</c:forEach>
					</select>
		</fieldset>
	
		<fieldset>
			<textarea placeholder="동행 소개를 적어주세요." tabindex="3"
				required></textarea>
		</fieldset>
		<fieldset>
			<button name="submit" type="submit" id="contact-submit"
				data-submit="...Sending">Submit</button>
		</fieldset>
		<input type="hidden" name="planNo" value="${planNo }">
		<input type="hidden" name="title" value="${title }">		
	</form>
</body>


</html>