<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="/resources/plan/css/plandetail.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn3.devexpress.com/jslib/20.1.7/css/dx.common.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn3.devexpress.com/jslib/20.1.7/css/dx.softblue.css" />
<%@ include file="../includes/sidebar_setting.jsp"%>
<script src="/resources/plan/js/plandetail.js"></script>
<script
	src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xx15e7f0ab6ce4456f9a97564f50cf5e2f"></script>
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn3.devexpress.com/jslib/20.1.7/js/dx.all.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%@include file="../includes/sidebar.jsp"%>
	<input type="hidden" id="uid" value="${userInfo.userId }">
	<input type="hidden" id="planno" value="${planNo }">
	<main class="page-content">
		<div class="container jumbotron">
			<div class="row">
				<div class="col-md-8 left-map">
					<div id="map_div" style="width: 90%"></div>
				</div>
				<div class="col-md-4 ml-auto right-planlist">
					<div class="row">
						<h3 id="p_title">
							<c:out value="${planner.planTitle }"></c:out>
						</h3>
						<button type="button" id="btn-plan"
							style="border: 0; outline: 0; margin-left: 50%">정보</button>
						<button type="button" id="btn-planner"
							style="border: 0; outline: 0">플래너</button>
					</div>
					<hr>
					<div id="plan-info">
						<div>
							<h4>Day 1</h4>
							<hr>
							<ul id="day1">
								<c:set var="day" value="1"></c:set>
								<c:forEach items="${planList }" var="list" varStatus="status">
									<c:if test="${list.is_insertAfter eq 'N'}">
										<c:if test="${day ne list.planDay }">
							</ul>

							<button type="button" class="btn btn-primary optimize"
								value="optimize">최적화</button>
							<hr>
						</div>
						<div>
							<h4>
								<c:out value="Day ${list.planDay }"></c:out>
							</h4>
							<ul id="day${list.planDay }">
								<hr>
								</c:if>
								<li class="ui-state-default" id='${status.index }'>
									<div class="row">
										<input type="hidden" name="planData" value='${list }'>
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
								</li>
								<c:set var="day" value="${list.planDay }"></c:set>
								</c:if>
								<c:if test="${list.is_insertAfter eq 'Y'}">
									<input type="hidden" name="scheduleData" value='${list }'>
								</c:if>
								</c:forEach>
							</ul>
							<button type="button" class="btn btn-primary optimize"
								value="최적화">최적화</button>
						</div>
					</div>
					<div id="planner-info" style="display: none">
						<div class="planner-img" style="padding-bottom: 10px;">
							<img style="width: 50%;" src="${planner.planImg }"
								id="img-planner"></img>
						</div>
						<textarea name="info" id="input-info" rows="3"
							style="width: 100%; max-height: 70px;">${planner.info }</textarea>
						<form id="upload-form" method="post" enctype="multipart/from-data"
							action="/upload/plan/img">
							<input type="file"
								style="padding-bottom: 10px; padding-top: 10px;"
								name="plannerImg"> <input type="hidden" name="planNo"
								value="${planNo }">
						</form>
						<button type="button" id="modify-planner"
							style="border: 0; outline: 0">플래너 수정</button>
					</div>
				</div>
				<div style="padding-top: 20px">
					<p id="result"></p>
				</div>
			</div>
		</div>
		<div class="container bottom-calendar jumbotron">
			<div id="scheduler"></div>
			<c:if test="${isModify eq 'N'}">
				<div id="btn-save" style="padding: 20px 0 35px 0">
					<button type="button" id="btn-save" class="btn btn-primary"
						style="float: right">저장</button>
				</div>
			</c:if>
			<c:if test="${isModify eq 'Y'}">
				<div id="btn-modify" style="padding: 20px 0 35px 0">
					<button type="button" id="btn-modify" class="btn btn-primary"
						style="float: right">수정</button>
				</div>
			</c:if>
		</div>
		<form action="/plan/save/schedule" method="post" id="scheduleForm">
			<input type="hidden" id="schedule-plan" name="schedule" value="">
		</form>
		<form action="/plan/modify" method="get" id="ModifyForm">
			<input type="hidden" id="planNo" name="planNo" value="${planNo }">
		</form>
		<div class="container datatest jumbotron">
			<c:out value="${planList }"></c:out>
		</div>
	</main>
	</div>
</body>
</html>