<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/budget/css/budget.css" rel="stylesheet">
<%@ include file="../includes/sidebar_setting.jsp"%>
<script src="/resources/budget/js/budget.js"></script>
</head>

<!-- Josh Channin -->
<body>
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
					<c:forEach items="${allPlan }" var="list" varStatus="status">
						<div class="cc" id="${list.planNo }">
							<input type="hidden" class="planNo" value="${list.planNo }">
							<div class="row">
								<div class="cc-img-main col-md-7" style="background-image:url(${list.planImg})"></div>
								<div class="cc-information col-md-4 ml-auto">
									<div class="cc-num"><c:out value="${list.planTitle }"></c:out></div>
									<div class="cc-date"><c:out value="${list.planNo }"><</c:out></div>
								</div>
							</div>
						</div>
					</c:forEach>
					</div>
				</aside>
				<content class="right-trans">
				<h1>Current Balance</h1>
				<h4 id="balance"></h4>
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
				</content>
			</div>
		</div>

		<div class="modal">
			<div class="modal-body">
				<h3>Add a New Card</h3>
				<h5>Select a card on the left and enter the information</h5>
				<div class="modal-close">x</div>
				<div class="modal-cards">
					<div class="md-cc visa">
						<div class="cc-img visa"></div>
					</div>
					<div class="md-cc amex">
						<div class="cc-img amex"></div>
					</div>
					<div class="md-cc mc">
						<div class="cc-img mc"></div>
					</div>
				</div>
				<form>
					<input type="text" id="ccnum" disabled> <select
						name="month" id="month" required disabled>
						<option value="">Month</option>
						<option value="01">Janaury</option>
						<option value="02">February</option>
						<option value="03">March</option>
						<option value="04">April</option>
						<option value="05">May</option>
						<option value="06">June</option>
						<option value="07">July</option>
						<option value="08">August</option>
						<option value="09">September</option>
						<option value="10">October</option>
						<option value="11">November</option>
						<option value="12">December</option>
					</select> <select name="year" id="year" required disabled>
						<option value="">Year</option>
						<option value="16">2016</option>
						<option value="17">2017</option>
						<option value="18">2018</option>
						<option value="19">2019</option>
						<option value="20">2020</option>
						<option value="21">2021</option>
						<option value="22">2022</option>
						<option value="23">2023</option>
						<option value="24">2024</option>
					</select>
				</form>
				<button class="modal-add-cc">Add</button>
			</div>
		</div>
	</main>
</body>
</html>