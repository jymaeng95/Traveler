<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css"
	rel="stylesheet">
<link href="/resources/plan/css/information.css" rel="stylesheet">
<link href="/resources/util/css/font.css" rel="stylesheet">
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.js"></script>
<script src="/resources/plan/js/information.js"></script>
<script src="https://kit.fontawesome.com/27324e8e3c.js" crossorigin="anonymous"></script>

</head>
<body>
	<input type="hidden" name="contentId" id="contentId" value="${contentId }">
	<input type="hidden" name="contentTypeId" id="contentTypeId" value="${contentTypeId }">
	
	<div class="wrapping">
		<main>
			<section class="banner"
				style="margin-top: 0px; padding-top: 0px;">
				<div class="inner_wrap">
					<div class="photo">
						<img class="fw lazyloaded" id="main-img"
							src="">
					</div>
					<!--Main Info-->
					<div class="info">
						<div class="titleArea">
							<h2 style="font-weight : bold;"class="title"></h2>
							<div>
								<div class="imoticon"
									style="display: inline-block; padding-left: 3px; padding-right: 3px; background: #FFFFE0; padding-top: 2px; padding-bottom: 2px; font-size: 8pt">
									
								</div>
							</div>
							<h4 style="font-weight : bold;"class="address"><i class="fas fa-map-pin"></i></h4>
						</div>
						<div class="div-overview">
							<p id="overview"></p>
						</div>
					</div>
				</div>
			</section>
			
			<section class="stu_section stu_exp_list stu_clearfix"
				style="margin-bottom: 10px;">
				<div class="inner_wrap">
					<div style="padding-left: 20px; padding-right: 20px" class="details">
				<!-- 		<div
							style="display: inline-block; padding-left: 3px; padding-right: 3px; background: #FF4500; color: #fff; padding-top: 2px; padding-bottom: 2px; font-size: 8pt">
							<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>한국방문자
							입국금지
						</div>

						<p>▸3.17.부터 5.11.까지 쉥겐지역 및 EU 회원국이 아닌 제3국 국적자 입국금지(영국 국 적자는
							예외)(프랑스 경유를 통한 한국 귀국은 허용) ※ △EU 회원국내 체류증 소지자, △제3국 국적자이나 의료 관련
							종사자는 입국 가능 ※ 주변국에서 육로(기차포함)로 프랑스 입국 불가</p> -->
					</div>
				</div>
			</section>
		</main>
	</div>
</body>


</html>