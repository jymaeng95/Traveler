<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- custom css -->
<%@ include file="../includes/sidebar_setting.jsp"%>
<link href="/resources/login/css/login.css" rel="stylesheet">
<script src="/resources/login/js/login.js" charset="utf-8">

	
</script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

</head>
<body>
	<%@include file="../includes/sidebar.jsp"%>
	<!--
  This was created based on the Dribble shot by Deepak Yadav that you can find at https://goo.gl/XRALsw
  I'm @hk95 on GitHub. Feel free to message me anytime.
-->
	<main class="page-content">
		<div class="container">
			<div class="body">
				<div class="veen">
					<div class="login-btn splits">
						<p>Already an user?</p>
						<button class="active">Login</button>
					</div>
					<div class="rgstr-btn splits">
						<p>Don't have an account?</p>
						<button>Register</button>
					</div>
					<div class="wrapper">
						<form id="login" tabindex="500" name="loginForm">
							<h3>Login</h3>
							<div class="uid">
								<input type="text" id="uid_l" name="uid"> <label>User
									ID</label>
							</div>
							<div class="passwd">
								<input type="password" id="upw_l" name="upw"> <label>Password</label>
							</div>
							<div class="submit">
								<button type="button" class="dark" id="login-btn">Login</button>
							</div>
							<hr>
							<!-- 카카오 로그인 연동 start -->
							<!-- 카카오 로그인 연동 start -->
							<div class="submit">
								<a
									href="https://kauth.kakao.com/oauth/authorize?client_id=95b818577076aee958016c6878581e88&redirect_uri=http://localhost:8080/oauth&response_type=code"
									class="btn_kakao"> <img
									src="/resources/login/assets/img/kakao_login_medium.png"
									alt="KAKAO">
								</a>
							</div>
						</form>
						<form id="register" tabindex="502" name="regForm">
							<h3>Register</h3>
							<div class="uid">
								<input type="text" id="uid_r" name="uid"> <label>User
									ID</label>
							</div>
							<div class="passwd">
								<input type="password" id="upw_r" name="upw"> <label>Password</label>
							</div>
							<div class="email">
								<input type="text" id="email" name="email"> <label>E-mail</label>
							</div>
							<div class="nickname">
								<input type="text" id="nickname" name="nickname"> <label>Nickname</label>
							</div>
							<div class="signup">
								<button type="button" class="dark" id="reg-btn">Register</button>

							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>
	</div>
</body>
</html>