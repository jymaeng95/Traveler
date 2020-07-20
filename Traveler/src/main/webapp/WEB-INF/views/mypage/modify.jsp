<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../includes/sidebar_setting.jsp"%>
<link href="/resources/mypage/css/modify.css" rel="stylesheet">
<script src="/resources/mypage/js/modify.js"></script>
<title>Insert title here</title>
</head>
<body style="background-color: #C3D9F0;">
	<%@include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<div class="container">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<form class="form-card" name="infoForm" method="post" id="infoForm">
					<fieldset class="form-fieldset">
						<legend class="form-legend">회원 정보 수정</legend>

						<div class="form-element form-input">
							<input id="uid" name="userId" class="form-element-field"
								value="${userInfo.userId }" placeholder="Please fill in your ID"
								type="text" required readonly />
							<div class="form-element-bar"></div>
							<label class="form-element-label"
								for="field-omv6eo-metm0n-5j55wv-w3wbws-6nm2b9">ID</label>
						</div>
						<div class="form-element form-input form-has-error">
							<input id="currentpw" name="currentpw"
								class="form-element-field -hasvalue" placeholder=" "
								type="password" required />
							<div class="form-element-bar"></div>
							<label class="form-element-label"
								for="field-x98ezh-s6s2g8-vfrkgb-ngrhef-atfkop">Current
								Password</label>
						</div>
						<div class="form-element form-input form-has-error">
							<input id="upw" name="userPw"
								class="form-element-field -hasvalue" placeholder=" "
								type="password" required />
							<div class="form-element-bar"></div>
							<label class="form-element-label"
								for="field-x98ezh-s6s2g8-vfrkgb-ngrhef-atfkop">Password</label>
						</div>
						<div class="form-element form-input">
							<input id="email" name="email" class="form-element-field"
								placeholder=" " type="email" value="${userInfo.email }" required />
							<div class="form-element-bar"></div>
							<label class="form-element-label"
								for="field-uyzeji-352rnc-4rv3g1-bvlh88-9dewuz">Email</label> <small
								class="form-element-hint">ex) xxx@gmail.com</small>
						</div>

						<input type="hidden" name="c_nickname" id="cur-nickname"
							value="${userInfo.nickname }">
						<div class="form-element form-input form-has-error">
							<input id="nickname" name="nickname"
								class="form-element-field -hasvalue" placeholder=" " type="text"
								value="${userInfo.nickname }" required />
							<div class="form-element-bar"></div>
							<label class="form-element-label"
								for="field-x98ezh-s6s2g8-vfrkgb-ngrhef-atfkop">Nickname</label>
						</div>

						<div class="form-element form-input form-has-error">
							<input id="iskakao" name="is_kakao"
								class="form-element-field -hasvalue" placeholder=" " type="text"
								value="${userInfo.is_kakao }" required readonly />
							<div class="form-element-bar"></div>
							<label class="form-element-label"
								for="field-x98ezh-s6s2g8-vfrkgb-ngrhef-atfkop">카카오 회원 여부</label>
						</div>

					</fieldset>
					<div class="form-actions">
						<button class="form-btn" type="button" id="btn-submit">Send
							inquiry</button>
						<button class="form-btn-cancel -nooutline" type="button"
							id="btn-cancel">Cancel</button>
					</div>
				</form>
			</div>
		</div>
	</main>
	</div>
</body>
</html>