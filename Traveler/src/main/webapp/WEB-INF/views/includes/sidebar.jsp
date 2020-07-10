<div class="page-wrapper chiller-theme toggled">
	<a id="show-sidebar" class="btn btn-sm btn-dark" href="#"> <i
		class="fas fa-bars"></i>
	</a>
	<nav id="sidebar" class="sidebar-wrapper">
		<div class="sidebar-content">
			<div class="sidebar-brand">
				<a href="/">Traveler</a>
				<div id="close-sidebar">
					<i class="fas fa-times"></i>
				</div>
			</div>
			<div class="sidebar-header">
				<div class="user-pic">
					<img class="img-responsive img-rounded"
						src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg"
						alt="User picture">
				</div>
				<div class="user-info">

					<c:if test="${userId == null }">
						<span class="user-name">After Login</span>
					</c:if>
					<c:if test="${userId != null }">
						<span clss="user-name">${userId }</span>
						<span class="user-role">Administrator</span>
					</c:if>
				</div>
			</div>
			<!-- sidebar-header  -->

			<!-- sidebar-search  -->
			<div class="sidebar-menu">
				<ul>
					<c:if test="${userId == null }">
					<li class="sidebar"><a href="/login/index"> <i
							class="fa fa-globe"></i> <span>Login</span>
					</a>
					</li>
					</c:if>
					<li class="header-menu"><span>General</span></li>
					<li class="sidebar-dropdown"><a href="#"> <i
							class="fa fa-tachometer-alt"></i> <span>Plan</span>
					</a>
						<div class="sidebar-submenu" style="display: none;">
							<ul>
								<li><a href="#">새로운 계획<span
										class="badge badge-pill badge-success">Pro</span>
								</a></li>
								<li><a href="#">기존 계획</a></li>
								<li><a href="#">Dashboard 3</a></li>
							</ul>
						</div></li>
					<li class="sidebar-dropdown"><a href="#"> <i
							class="fa fa-shopping-cart"></i> <span>Spot</span> <span
							class="badge badge-pill badge-danger">3</span>
					</a>
						<div class="sidebar-submenu" style="display: none;">
							<ul>
								<li><a href="#">Products </a></li>
								<li><a href="#">Orders</a></li>
								<li><a href="#">Credit cart</a></li>
							</ul>
						</div></li>
					<li class="sidebar-dropdown"><a href="#"> <i
							class="far fa-gem"></i> <span>Account</span>
					</a>
						<div class="sidebar-submenu" style="display: none;">
							<ul>
								<li><a href="#">General</a></li>
								<li><a href="#">Panels</a></li>
								<li><a href="#">Tables</a></li>
								<li><a href="#">Icons</a></li>
								<li><a href="#">Forms</a></li>
							</ul>
						</div></li>
					<c:if test="${userId != null }">
						<li class="sidebar-dropdown active"><a href="#"> <i
								class="fa fa-chart-line"></i> <span>Mypage</span>
						</a>
							<div class="sidebar-submenu" style="display: block;">
								<ul>
									<li><a href="/mypage/mypage">mypage</a></li>
									<li><a href="#">북마크</a></li>
									<li><a href="#">쪽지</a></li>
									<li><a href="#">Histogram</a></li>
								</ul>
							</div></li>
					</c:if>

				</ul>
			</div>
			<!-- sidebar-menu  -->
		</div>
		<!-- sidebar-content  -->
		<div class="sidebar-footer">
			<c:if test="${userId != null }">
				<a href="/mypage/message"> <i class="fa fa-envelope"></i> <span
					class="badge badge-pill badge-success notification">7</span>
			</a> 
			</c:if>
			<a href="/mypage/modify"> <i class="fa fa-cog"></i> <span class="badge-sonar"></span>
			</a> 
			<c:if test="${userId != null }">
				<a href="/logout"> 
			</c:if>
			<c:if test="${userId == null }">
				<a href="#"> 
			</c:if>
			<i class="fa fa-power-off"></i>
			</a>
		</div>
	</nav>