<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- <link href="/resources/plan/css/index.css" rel="stylesheet"> -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<%@ include file="../includes/sidebar_setting.jsp"%>
<script src="/resources/plan/js/index.js"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<!-- <script src="/resources/plan/js/index.js"></script> -->
<title>Insert title here</title>
</head>
<body>
	<%@include file="../includes/sidebar.jsp"%>
	<input type="hidden" name="userId" id="userId"
		value="${userInfo.userId }">
	<main class="page-content">
		<div class="container" style="padding: 0">
			<section class="bg-light" style="margin-top: 15px;">
				<div class="container"
					style="padding-top: 0px; margin-top: 0px; margin-bottom: 10px;">
					<h2 class="pt-3"
						style="padding-left: 20px; font-size: 18pt; font-weight: 300; color: #696969">
						내 제주도 여행</h2>
					<div style="clear: both"></div>
					<!-- c태그로 if문으로 제어  -->
					<div
						style="text-align: center; padding-top: 40px; padding-bottom: 40px; color: #c0c0c0; font-size: 12pt">
						새로운 여행을 만들어 보세요!</div>
				</div>
			</section>
			<section
				style="padding-top: 30px; padding-bottom: 20px; margin-top: 0px;">
				<div class="container pt-3">
					<div class="row">
						<!-- left content -->
						<div class="col-lg-6">
							<img class="plan_img"
								src="/resources/assets/img/plan_images/plan.png">
						</div>
						<!-- right content -->
						<div class="jumbotron bg-light text-center col-lg-5 ml-auto">
							<h2 style="font-size: 18pt; font-weight: 300; color: #696969">
								Traveler로</h2>
							<h2 style="font-size: 18pt; font-weight: 300; color: #696969">
								새로운 여행을 계획해 봅시다.</h2>
							<div class="row">
								<div class="col-sm-6">
									<button type="button" id="new_plan" style="border-radius: 30px; margin-right: 5px; text-align: center; margin-top: 70%; width: 100%; text-decoration: none; font-weight: 300; color: #000; font-size: 14pt; display: inline-block; padding: 10px 20px; border: 1px solid"> <!-- href="/plan/create" -->
										새 로운 여행</button>
								</div>
								<div class="col-sm-6">
									<!-- 임시 마이플랜으로 이동  -->
									<a href="/plan/create2"
										style="border-radius: 30px; margin-right: 5px; text-align: center; margin-top: 70%; width: 100%; text-decoration: none; font-weight: 300; color: #000; font-size: 14pt; display: inline-block; padding: 10px 20px; border: 1px solid">스팟
										찾기</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</main>

	<div class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" id="myModal" aria-hidden="true">
		<div class="modal-dialog"
			style="max-width: 100%; width: auto; display: table;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">새 여행 만들기</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>


				</div>
				<form id="plan_info" method="get" action="/plan/create">
				<div class="modal-body">
					<input type="text" id="p_title" name="plan_title"
						placeholder="여행제목" style="width: 308px"><br> <input
						type="text" id="p_date" name="plan_date" placeholder="여행날짜" style="width: 100%"
						readonly />
					<input type="hidden" name="total_date" id="t_date" />
				</div>
				</form>
				<div class="modal-footer">
					<button class="btn btn-primary" type="button" id="confirm">예</button>
					<button class="btn btn-primary" type="button" data-dismiss="modal">아니요</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>