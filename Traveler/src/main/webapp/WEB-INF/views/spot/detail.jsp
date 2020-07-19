<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<%@ include file="../includes/sidebar_setting.jsp"%>
<!-- <link href="/resources/spot/css/detail.css" rel="stylesheet">
 -->
 <script src="/resources/spot/js/detail.js"></script>

</head>
<body>
	<input type="hidden" name="contentId" id="contentId"
		value=${contentId }>
	<input type="hidden" name="contentTypeId" id="contentTypeId"
		value=${contentTypeId }>
	<input type="hidden" name="userId" id="userId" value=${userInfo.userId }>

	<%@include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<div class="container">
			<div class="row">
				<div class="col-lg-11 ">
					<h2 class="page-header" id="title"></h2>
				</div>
				<div class="col-lg-1">
					<button type="button" class="btn btn-default" id="bookmark">
						
							<i class="far fa-star" id="iconMark"></i>
					</button>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-lg-8">
					<img class="img-responsive"
						src="/resources/assets/img/spot_images/no_img.png" id="spot_img"
						style="max-width: 100%">
				</div>
				<div class="col-lg-4">
					<h3>상세 정보</h3>
					<ul class="details">
					</ul>
				</div>
			</div>
			<br>
			<div class="row">

				<div class="col-lg-12">
					<h3>장소 개요</h3>
					<hr>
					<p id="overview"></p>
				</div>
				<div class="col-lg-12">
					<h3 class="page-header">관련 이미지</h3>
				</div>
			</div>
			<hr>
			<div class="row">
				<c:forEach var="i" begin="0" end="3">
					<div class="col-sm-3 col-xs-6">
						<a href="#" class="thumbnail"> <img
							class="img-responsive portfolio-item" id="img-sub${i }"
							src="http://placehold.it/500x300" style="max-width: 100%">
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</main>
	</div>
</body>
</html>