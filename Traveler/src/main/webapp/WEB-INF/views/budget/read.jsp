<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/budget/css/read.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<%@ include file="../includes/sidebar_setting.jsp"%>
<script src="/resources/budget/js/read.js"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
</head>

<!-- Josh Channin -->
<body style="background-color: #C3D9F0;">
	<%@ include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<div>
			<div class="wallet">
				<aside class="left-wallet">
					<div class="wallet-head">
						<c:if test="${recommend eq '일반' }">
						<h3><c:out value="${planner.userId }님의 예산"></c:out></h3>
						</c:if>
						<c:if test="${recommend ne '일반' }">
						<h3>Traveler가 추천하는</h3>
						<h3><c:out value="${recommend } 예산"></c:out></h3>
						</c:if>
						
						<!-- 		<div class="modal-open">+</div> -->
					</div>
					<div class="cc-select">
						<div class="cc" id="${planner.planNo }">
							<input type="hidden" class="planNo" value="${planner.planNo }">
							<div class="row">
								<div class="cc-img-main col-md-7"
									style="background-image:url(${planner.planImg})"></div>
								<div class="cc-information col-md-4 ml-auto">
									<div class="cc-num">
										<c:out value="${planner.planTitle }"></c:out>
									</div>
									<div class="cc-date">
										<c:out value="${planTotalDate}일간의 여행"><</c:out>
									</div>
								</div>
							</div>
						</div>
					</div>
				</aside>
				<content class="right-trans">
				<h1 id="planTitle">
					<c:out value="${planner.planTitle }"></c:out>
				</h1>
				<div class="trans-list">
					<c:forEach items="${budget }" var="list" varStatus="status">
						<div class="trans trans-each">
							<div class="trans-details">
								<span class="action"></span>
								<h3 class="trans-title"><c:out value="${list.title }"></c:out></h3>
								<div class="row">
									<h5 class="trans-descript"><c:out value="${list.descript }"></c:out></h5>
									<h5 class="trans-cat"><c:out value="${list.cat }"></c:out></h5>
									<h5 class="trans-planDate"><c:out value="${list.planDate }"></c:out></h5>
								</div>
							</div>
							<div class="trans-price">
								<c:if test="${list.income > 0}">
									<h4 class="trans-price income" style="color: rgb(113, 207, 66)"><c:out value="${list.income }\\"></c:out></h4>
								</c:if>
								<c:if test="${list.expend > 0 }">
									<h4 class="trans-price expend" style="color: #ff3232"><c:out value="${list.expend }\\"></c:out></h4>
								</c:if>
								<c:if test="${list.income == 0 && list.expend == 0 }">
									<h4 class="trans-price">0\</h4>
								</c:if>
							</div>

						</div>
					</c:forEach>
				</div>
				<div class="row trans-footer">
					<div class="row btn-div">
						<h4 id="balance" style="margin-right: 20px;"><c:out value="총계 : ${budget[0].total }\\"></c:out></h4>
						<div style="padding-top: 2%"></div>
						<button type="button" id="btn-back" class="btn btn-dark" onclick="history.go(-1)">돌아가기</button>
					</div>
				</div>
				</content>
			</div>
		</div>
	</main>
</body>
</html>