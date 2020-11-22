<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/budget/css/recommend.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<%@ include file="../includes/sidebar_setting.jsp"%>
<script src="/resources/budget/js/recommend.js"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
	<style>
.pagination {
  display: inline-block;
}

.pagination li {
  color: black;
  float: left;
  padding: 6px 12px;
  text-decoration: none;
  border: 1px solid #ddd;
}

.pagination a {
  color: black;
  font-size: 12px;
}

.pagination li.active {
  background-color: #c8c8c8;
  color: white;
  border: 1px solid #c8c8c8;
}

.pagination li:hover:not(.active) {background-color: #ddd;}

.pagination li:first-child {
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}

.pagination li:last-child {
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}
</style>
</head>
<body>
	<%@ include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<section class="notice">
			<div class="page-title">
				<div class="container">
					<h3 id="index-title" style="margin-top: 5%;">Traveler가 추천하는 예산
					</h3>
				</div>
			</div>

			<!-- board seach area -->
			<div id="recommend-list">
				<div class="container">
					<div class="search-window">
						<table class="board-table">
							<thead>
								<tr>
									<th scope="col" class="th-rec">추천 구분</th>
									<th scope="col" class="th-num">번호</th>
									<th scope="col" class="th-title">작성자</th>
									<th scope="col" class="th-uid">여행 제목</th>
									<th scope="col" class="th-day">여행 일수</th>
									<th scope="col" class="th-total">총액</th>
									<th scope="col" class="th-regdate">등록일자</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${maxPlan ne null }">
									<tr class="plan-recommend">
										<c:if test="${rcmPlanSize > 1}">
											<td class="recommend-standard">총계 최대</td>
										</c:if>
										<c:if test="${rcmPlanSize eq 1}">
											<td class="recommend-standard">총계 기반</td>
										</c:if>
										<td class="recommend-planno"><c:out
												value="${maxPlan.planNo }"></c:out></td>
										<td class="recommend-userid"><c:out
												value="${maxPlan.userId }"></c:out></td>
										<td class="recommend-plantitle"><c:out
												value="${maxPlan.planTitle }"></c:out></td>
										<td class="recommend-totaldate"><c:out
												value="${planTotalDate}일간 여행"></c:out></td>
										<td class="recommend-total"><c:out
												value="${maxBudget.total}"></c:out></td>
										<td class="recommend-regdate"><c:out
												value="${maxBudget.reg_date}"></c:out></td>
									</tr>
								</c:if>
								<c:if test="${minPlan ne null }">
									<tr class="plan-recommend">
										<td class="recommend-standard">총계 최소</td>
										<td class="recommend-planno"><c:out
												value="${minPlan.planNo }"></c:out></td>
										<td class="recommend-userid"><c:out
												value="${minPlan.userId }"></c:out></td>
										<td class="recommend-plantitle">><c:out
												value="${minPlan.planTitle }"></c:out></td>
										<td class="recommend-totaldate"><c:out
												value="${planTotalDate}일간 여행"></c:out></td>
										<td class="recommend-total"><c:out
												value="${minBudget.total}"></c:out></td>
										<td class="recommend-regdate"><c:out
												value="${minBudget.reg_date}"></c:out></td>
									</tr>
								</c:if>
								<c:if test="${maxPlan eq null }">
									<tr>
										<td colspan="5" class="none">아직 생성된 예산이 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<!-- board list area -->

			<div id="board-list">
				<div class="container">
					<h3 id="index-title" style="margin-top: 5%; text-align:center;">
						<c:out value="${planTotalDate }일간의 여행 예산"></c:out>
					</h3>
				</div>
				<div class="container">
					<table class="board-table">
						<thead>
							<tr>
								<th scope="col" class="th-num">번호</th>
								<th scope="col" class="th-title">작성자</th>
								<th scope="col" class="th-uid">여행 제목</th>
								<th scope="col" class="th-day">여행 일수</th>
							</tr>
						</thead>
						<c:if test="${allPlan eq null }">
							<tr>
								<td colspan="4" class="none">아직 생성된 예산이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${allPlan ne null }">
							<tbody>
								<c:forEach items="${allPlan}" var="list" varStatus="status">
									<tr class="plan-each">
										<td class="planNo"><c:out value="${list.planNo }"></c:out></td>
										<td class="userid"><c:out value="${list.userId }"></c:out></td>
										<td class="plan-title"><c:out value="${list.planTitle }"></c:out></td>
										<td class="planTotalDay"><c:out
												value="${planTotalDate}일간 여행"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>
					</table>
					<div class="row">
						<div class="col-sm-7">
							<ul class="pagination" id="pagination-demo"
								style="margin-top: 10px; justify-content: center;">
								<c:if test="${pageMaker.prev }">
									<li class="paginate_button previous"
										style="padding-right: 5px;"><a
										href="${pageMaker.startPage -1 }">&laquo;</a></li>
								</c:if>
								<c:forEach var="num" begin="${pageMaker.startPage }"
									end="${pageMaker.endPage }">
									<li class="paginate_button ${pageMaker.pageNum == num ? "
										active":"" }"
								style="padding-right: 5px;"><a
										href="${num }">${num} </a></li>
								</c:forEach>
								<c:if test="${pageMaker.next }">
									<li class="paginate_button next"><a
										href="${pageMaker.endPage +1 }">&raquo;</a></li>
								</c:if>
							</ul>
						</div>
						<form id='actionForm' action="/budget/recommend" method='get'>
							<input type='hidden' name='pageNum' value='${pageMaker.pageNum }'>
							<input type="hidden" name="planTotalDate"
								value="${planTotalDate }">
						</form>
						<div class="col-sm-5">
							<button type="button" class="btn btn-dark" id="btn-mybudget"
								style="float: right; margin-top: 2%;" onclick="history.go(-1)">돌아가기</button>
							<button type="button" class="btn btn-dark" id="btn-graph"
								style="float: right; margin-top: 2%; margin-right: 2%;"
								value="${ planTotalDate}">
								<c:out value="${planTotalDate }일간 예산 그래프"></c:out>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div id="budget-stastic" style="display: none;">
				<div class="container ">

					<div class="row">
						<div id="budget-chart" style="width: 50%; height: 500px;"
							class="ml-auto"></div>
						<div id="cat-chart" style="width: 50%; height: 500px;"></div>
					</div>
					<button type="button" id="btn-back-budget" class="btn btn-dark"
						style="float: right;">돌아가기</button>
				</div>
			</div>
		</section>
	</main>
</body>
</html>