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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>

<!-- Josh Channin -->
<body style="background-color:#C3D9F0;">
	<%@ include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<div>
			<div class="wallet">
				<aside class="left-wallet">
					<div class="wallet-head">
						<h1>My Wallets</h1>
						<!-- 		<div class="modal-open">+</div> -->
					</div>
					<div class="cc-select">
							<div class="cc" id="${list.planNo }">
								<input type="hidden" class="planNo" value="${list.planNo }">
								<div class="row">
									<div class="cc-img-main col-md-7"
										style="background-image:url(${list.planImg})"></div>
									<div class="cc-information col-md-4 ml-auto">
										<div class="cc-num">
											<c:out value="${list.planTitle }"></c:out>
										</div>
										<div class="cc-date">
											<c:out value="${list.planNo }"><</c:out>
										</div>
									</div>
								</div>
							</div>
					</div>
				</aside>
				<content class="right-trans">
				<h1 id="planTitle"></h1>
				<input type="hidden" name="planNo" id="planNo">
				<input type="hidden" name="planTotalDate" id="planTotalDate">
				<div class="trans-list">
						<div class="trans trans-type">
							<div class="trans-details">
								<span class="action"></span>
								<h3 class="trans-name"></h3>
								<h5 class="trans-type-date"></h5>
							</div>
							<div class="trans-amt">
								<h4 class="trans-amt amt-blue"></h4>
							</div>
					</div>
				</div>
				<div class="row trans-footer">
					<div class="row btn-div">
						<h4 id="balance" style="margin-right:20px;"></h4>
						<div style="padding-top:2%">
						</div>
						<button type="button" id="btn-save" class="btn btn-primary">돌아가기</button>
					
					</div>
				</div>
				</content>
			</div>
		</div>
	</main>
</body>
</html>