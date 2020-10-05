<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/resources/budget/css/index.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<%@ include file="../includes/sidebar_setting.jsp"%>
<script src="/resources/budget/js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>
	<%@ include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<section class="notice">
			<div class="page-title">
				<div class="container">
					<c:if test="${countUserPlan eq 0 }">
						<a href="/plan/plan"><h6 style="text-align: center;">
								생성할 예산이 존재하지 않습니다.<br> 새로운 계획 만들러 가기!
							</h6></a>
						<h3 id="index-title" style="margin-top: 5%;">다른 회원들의 예산</h3>
					</c:if>
					<c:if test="${countUserPlan ne 0 }">
						<h3>다른 회원들의 예산</h3>
					</c:if>
				</div>
			</div>

			<!-- board seach area -->
			<div id="board-search">
				<div class="container">
					<div class="search-window">
						<form id="searchForm" action="/accompany/board" method='get'>
							<div class="search-wrap">
								<label for="search" class="blind"></label> <select name='type'
									style="float: left; position: absolute; margin: 10px -80px 10px;">
									<option value="T"
										<c:out value="${pageMaker.cri.type eq 'T'?'selected' :'' }"/>>제목</option>
									<option value="C"
										<c:out value="${pageMaker.cri.type eq 'C'?'selected' :'' }"/>>내용</option>
									<option value="W"
										<c:out value="${pageMaker.cri.type eq 'W'?'selected' :'' }"/>>작성자</option>
								</select> <input type='search' name='keyword' id="search"
									placeholder="검색어를 입력해주세요."
									value='<c:out value="${pageMaker.cri.keyword }"/>' /> <input
									type='hidden' name='pageNum'
									value='<c:out value="${pageMaker.cri.pageNum }"/>'>
								<button class="btn btn-dark">검색</button>
								<!--                         <button id="searchDate">날짜로 검색</button> -->
							</div>
						</form>
					</div>
				</div>
			</div>

			<!-- board list area -->
			<div id="board-list">
				<div class="container">
					<table class="board-table">
						<thead>
							<tr>
								<th scope="col" class="th-num">번호</th>
								<th scope="col" class="th-title">작성자</th>
								<th scope="col" class="th-uid">여행 제목</th>
								<th scope="col" class="th-day">여행 일수</th>
								<th scope="col" class="th-date">여행 일자</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${allPlan}" var="list" varStatus="status">
								<tr>
									<td><c:out value="${list.planNo }"></c:out></td>
									<td><c:out value="${list.userId }"></c:out></td>
									<th class="plan-title"><a href="#!"><c:out
												value="${list.planTitle }"></c:out></a></th>
									<td id="planTotalDay"><c:out
											value="${allSchedule[status.index].planTotalDate}일간 여행"></c:out></td>
									<td id="planDate"><c:out
											value="${allSchedule[status.index].planDate}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:if test="${countUserPlan ne 0 }">
						<button type="button" class="btn btn-dark" id="btn-mybudget"
							style="float: right; margin-top: 2%;"
							onclick="location.href='/budget/budget'">내 예산 생성하기</button>
					</c:if>
					<button type="button" class="btn btn-dark" id="btn-graph"
						style="float: right; margin-top: 2%; margin-right:2%;">예산 추이 보기</button>
				</div>
			</div>
			<div id="budget-stastic" style="display: none;">
				<div class="container ">

					<div class="row">
						<div id="budget-chart" style="width: 50%; height: 500px;"
							class="ml-auto"></div>
						<div id="cat-chart" style="width: 50%; height: 500px;"></div>
					</div>
					<button type="button" id="btn-back-budget" class="btn btn-dark" style="float:right;">돌아가기</button>
					<button type="button" class="btn btn-dark" id="btn-mybudget"
							style="float: right; margin-right:2%;" onclick="location.href='/budget/budget'">내 예산 생성하기</button>
				</div>
			</div>
		</section>
	</main>
</body>
</html>