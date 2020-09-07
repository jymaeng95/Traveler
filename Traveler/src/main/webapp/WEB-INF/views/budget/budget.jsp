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
						</c:forEach>
					</div>
				</aside>
				<content class="right-trans">
				<h1 id="planTitle">Current Balance</h1>
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
					<h4 id="balance"></h4>
					<button type="button" id="btn-save" class="btn btn-primary">저장</button>
					<button type="button" id="btn-calc" class="btn btn-primary">계산</button>
					<button type="button" id="btn-graph" class="btn btn-primary">그래프</button>
				</div>
				</content>
			</div>
		</div>

		<div class="modal">
			<div class="modal-body">
				<h3 id="title">dfdfdf</h3>
				<div class="modal-close">x</div>
				<div class="modal-contents">
					<div style="padding: 10px;">
						<h5>간단한 수입/지출 정보를 입력하세요.</h5>
						<input type="text" id="info" placeholder="ex) 솜사탕">
					</div>
					<div class="category" style="padding: 10px;">
						<input type="radio" name="cat" value="관광">관광 <input
							type="radio" name="cat" value="숙박">숙박 <input type="radio"
							name="cat" value="식비">식비 <input type="radio" name="cat"
							value="교통">교통 <input type="radio" name="cat" value="기타">기타
					</div>
					<div style="padding: 10px;">
						<input type="text" name="price" id="price" placeholder="ex) 3000"
							numberOnly>
					</div>

				</div>
				<div style="padding: 10px;">
					<button type="button" id="income" value="income">수입</button>
					<button type="button" id="expend" value="expend">지출</button>
				</div>
				<!-- <button class="modal-add-cc">Add</button> -->
			</div>
		</div>

		<div class="add-budget-modal">
			<div class="modal-body">
				<h3>새로운 예산</h3>
				<div class="add-modal-close">x</div>
				<div class="modal-contents">
					<div style="padding:10px;">
						<h5>주제를 입력하세요.</h5>
						<input type="text" id="add-title" placeholder="ex) 제주공항">
					</div>
					<div style="padding: 10px;">
						<h5>간단한 수입/지출 정보를 입력하세요.</h5>
						<input type="text" id="add-info" placeholder="ex) 솜사탕">
					</div>
					<div style="padding:10px;">
						<h5>날짜를 입력해주세요.</h5>
						<input type="text" id="add-date" placeholder="ex) 2020-09-06">
					</div>
					<div class="category" style="padding: 10px;">
						<input type="radio" name="add-cat" value="관광">관광 <input
							type="radio" name="add-cat" value="숙박">숙박 <input type="radio"
							name="add-cat" value="식비">식비 <input type="radio" name="add-cat"
							value="교통">교통 <input type="radio" name="add-cat" value="기타">기타
					</div>
					<div style="padding: 10px;">
						<input type="text" name="price" id="add-price" placeholder="ex) 3000"
							numberOnly>
					</div>

				</div>
				<div style="padding: 10px;">
					<button type="button" id="add-income" value="income">수입</button>
					<button type="button" id="add-expend" value="expend">지출</button>
				</div>
				<!-- <button class="modal-add-cc">Add</button> -->
			</div>
		</div>
	</main>
</body>
</html>