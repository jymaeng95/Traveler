<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/budget/css/budget.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<%@ include file="../includes/sidebar_setting.jsp"%>
<script src="/resources/budget/js/budget.js"></script>
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
				<div id="piechart" class="trans-graph"></div>
				<div class="row trans-footer">
					<div class="row btn-div">
						<h4 id="balance" style="margin-right:20px;"></h4>
						<div style="padding-top:2%">
						<input type="radio" name="is_public" value="Y" id="radio-y"/>공개
						<input type="radio" name="is_public" value="N" id="radio-n" checked/>비공개
						</div>
						<button type="button" id="btn-save" class="btn btn-dark">저장</button>
						<button type="button" id="btn-calc" class="btn btn-dark">계산</button>
						<button type="button" id="btn-recommend" class="btn btn-dark">예산 추천</button>
					</div>
				</div>
				</content>
			</div>
		</div>

		<div class="modal">
			<div class="modal-body">
				<h3 id="title"></h3>
				<div class="modal-close">x</div>
				<div class="modal-contents">
					<div style="padding: 10px;">
						<h5>간단한 수입/지출 정보를 입력하세요.</h5>
						<input type="text" id="info" placeholder="ex) 솜사탕" required>
					</div>
					<div class="category" style="padding: 10px;">
						<input type="radio" name="cat" value="관광" checked>관광 <input
							type="radio" name="cat" value="숙박">숙박 <input type="radio"
							name="cat" value="식비">식비 <input type="radio" name="cat"
							value="교통">교통 <input type="radio" name="cat" value="기타">기타
					</div>
					<div style="padding: 10px;">
						<h6 id="fee"></h6>
						<input type="text" name="price" id="price" placeholder="ex) 3000"
							onkeypress="onlyNumber();" required>
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
						<input type="text" id="add-title" placeholder="ex) 제주공항" required>
					</div>
					<div style="padding: 10px;">
						<h5>간단한 수입/지출 정보를 입력하세요.</h5>
						<input type="text" id="add-info" placeholder="ex) 솜사탕" required>
					</div>
					<div style="padding:10px;">
						<h5>날짜를 입력해주세요.</h5>
						<input type="text" id="add-date" placeholder="ex) 2020-9-06" required>
					</div>
					<div class="category" style="padding: 10px;">
						<input type="radio" name="add-cat" value="관광" checked>관광 <input
							type="radio" name="add-cat" value="숙박">숙박 <input type="radio"
							name="add-cat" value="식비">식비 <input type="radio" name="add-cat"
							value="교통">교통 <input type="radio" name="add-cat" value="기타">기타
					</div>
					<div style="padding: 10px;">
						<input type="text" name="price" id="add-price" placeholder="ex) 3000"
							onkeypress="onlyNumber();" required>
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