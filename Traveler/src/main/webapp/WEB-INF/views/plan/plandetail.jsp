<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/plan/css/plandetail.css" rel="stylesheet">
<%@ include file="../includes/sidebar_setting.jsp"%>
<script src="/resources/plan/js/plandetail.js"></script>
<script
	src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xx15e7f0ab6ce4456f9a97564f50cf5e2f"></script>
<title>Insert title here</title>
</head>
<body>
	<%@include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<div class="container jumbotron">
			<div class="row">
				<div class="col-md-8 left-map">
					<div id="map_div" style="width: 90%"></div>
				</div>
				<div class="col-md-4 ml-auto right-planlist">

					<h4>Day 1</h4>
					<hr>
					<c:set var="day" value="1"></c:set>
					<c:forEach items="${planList }" var="list">
						<c:if test="${day ne list.planDay }">
							<h4>
								<c:out value="Day ${list.planDay }"></c:out>
							</h4>
							<hr>
						</c:if>
						<div class="row">
							<div class="col-lg-5">
								<img class="img-responsive" id="r_photo${i}" onclick=""
									style="cursor: pointer;" src="${list.img_src }" alt=""
									width="100" height="75">
							</div>
							<div class="col-lg-7">
								<span id="r_title${i}" style="font-size: 12pt;"><c:out
										value="${list.title }"></c:out></span> <span id="r_addr${i}"
									style="font-size: 10pt;"><c:out value="${list.addr }"></c:out></span>
							</div>
						</div>
						<hr>
						<c:set var="day" value="${list.planDay }"></c:set>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="container bottom-calendar jumbotron">sdfasdf</div>
	</main>
	</div>
</body>
</html>