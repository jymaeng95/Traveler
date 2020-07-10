<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../includes/sidebar_setting.jsp"%>

<!-- Custom CSS for the '3 Col Portfolio' Template -->
<link href="/resources/mypage/css/mypage.css" rel="stylesheet">
<title>Insert title here</title>
<style>
#sidebar * {
	font-size: 1.32rem;
}

#show-sidebar {
	position: fixed;
	left: 0;
	top: 10px;
	border-radius: 0 4px 4px 0px;
	width: 35px;
	transition-delay: 0.3s;
	font-size: 1.5rem;
}
</style>
</head>
<body>
	<%@include file="../includes/sidebar.jsp"%>
	<main class="page-content">
		<div class="container">
			<header>

				<div class="container">

					<div class="profile">

						<div class="profile-image">

							<img
								src="https://images.unsplash.com/photo-1513721032312-6a18a42c8763?w=152&h=152&fit=crop&crop=faces"
								alt="">

						</div>

						<div class="profile-user-settings">

							<h1 class="profile-user-name" value="${userId }"></h1>

							<button class="btn-mypage profile-edit-btn">Edit Profile</button>
							<button class="btn-mypage profile-edit-btn">Change Photo</button>
							<button class="btn profile-settings-btn"
								aria-label="profile settings">
								<i class="fas fa-cog" aria-hidden="true"></i>
							</button>
						</div>

						<div class="profile-stats">

							<ul>
								<li><span class="profile-stat-count">164</span> posts</li>
								<li><span class="profile-stat-count">188</span> followers</li>
								<li><span class="profile-stat-count">206</span> following</li>
							</ul>

						</div>

						<div class="profile-bio">

							<p>
								<span class="profile-real-name">Jane Doe</span> Lorem ipsum
								dolor sit, amet consectetur adipisicing elit 📷✈️🏕️
							</p>

						</div>

					</div>
					<!-- End of profile section -->

				</div>
				<!-- End of container -->

			</header>

			<main>

				<div class="container">

					<div class="gallery">
						<c:forEach var="i" begin="0" end="8">
							<div class="gallery-item" tabindex="0">

								<img
									src="https://images.unsplash.com/photo-1511765224389-37f0e77cf0eb?w=500&h=500&fit=crop"
									class="gallery-image" alt="">

								<div class="gallery-item-info">

									<ul>
										<li class="gallery-item-likes"><span
											class="visually-hidden">Likes:</span><i class="fas fa-heart"
											aria-hidden="true"></i> 56</li>
										<li class="gallery-item-comments"><span
											class="visually-hidden">Comments:</span><i
											class="fas fa-comment" aria-hidden="true"></i> 2</li>
									</ul>

								</div>

							</div>
						</c:forEach>

					</div>
					<!-- End of gallery -->

					<div class="loader"></div>

				</div>
				<!-- End of container -->

			</main>
		</div>
	</main>
	</div>
</body>
</html>
