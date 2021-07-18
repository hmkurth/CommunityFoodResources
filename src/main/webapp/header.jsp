<%@include file="taglib.jsp"%>

<body>
		<!-- Main navigation element for site, html5 elements used to improve rendering on assistive devices -->
		<nav class="navbar navbar-default navbar-fixed-top navbar-dark">
			<div class="top-header">
				<div class="container">

					<!-- Skip to content link allows users rendering the site on a screen reader to skip past navigational elements -->
					<div class="menu-block pull-left no-pad hidden-xs">
						<a href="indexMain.jsp" class="btn btn-link scroller"><em class="fa fa-level-down" aria-hidden="true"></em> Skip to Content &nbsp;</a>
					</div>

					<!-- This is optional, comment out if you do not need this set of dropdowns at the top   TODO try to get these langs to work!	-->
					<div class="dropdown pull-left">
						<button class="btn btn-link dropdown-toggle" type="button" id="lang_menu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						<em class="fa fa-globe" aria-hidden="true"></em> English
						<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="lang_menu">
							<li><a href="#">English</a></li>
							<li><a href="#">French</a></li>
							<li><a href="#">Spanish</a></li>
							<li><a href="#">Russian</a></li>
						</ul>
					</div>



					<!-- Enable the user to adjust the font size of the web page -->
					<div class="menu-block pull-left hidden-xs">Increase Text Size:</div>
					<button type="button" class="btn btn-link pull-left hidden-xs increase-font" aria-label="Increase"><em class="fa fa-plus" aria-hidden="true"></em></button>
					<button type="button" class="btn btn-link pull-left hidden-xs decrease-font" aria-label="Decrease"><em class="fa fa-minus" aria-hidden="true"></em></button>

					<!-- Secondary navigation and login dropdown -->
					<div class="menu-block pull-right no-pad">
						<!-- Login dropdown Insert login.jsp  but need a button that points to it for auth  -->
						<c:if test="${ empty loggedInUser}">
							<a href="${pageContext.request.contextPath}/loginAction" class=" btn-login" ><em class="fa fa-lock" aria-hidden="true"></em>  Sign In</a>
						</c:if>
						<c:if test="${ !empty loggedInUser}">
							<a href="${pageContext.request.contextPath}/logOut" class=" btn-login" ><em class="fa fa-lock" aria-hidden="true"></em>  Log Out</a>
						</c:if>
					</div>
				</div>
			</div>


			<!-- trying to make a row for the headline name, only for use on index page
			<c:if test="${ empty loggedInUser}">
				<div class="container navbar-header banner">
					<h2 class="text-center">Madison Community Food Resources</h2>
				</div>
			</c:if>

-->

				<div class="container">

					<!-- Mobile hamburger style menu -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top_main_navigation" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>


								<a class="navbar-brand pad-bottom" href="${pageContext.request.contextPath}/index.jsp">
									<img src="${pageContext.request.contextPath}/assets/img/logos/sunLiteLogoRound.png" class="img-circle"
										 alt="Back to home" />
								</a>

					 </div>


				<!-- Main site header -->
				<div class="collapse navbar-collapse  pull-right" id="top_main_navigation">
				<ul class="nav navbar-nav  pull-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Food Resources<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/forwardResources">Food Pantries</a></li>
								<li><a href="${pageContext.request.contextPath}/searchFoodResources">Search Food Resources</a></li>
								<li><a href="${pageContext.request.contextPath}/searchByLocation.jsp">Search By Location</a></li>
								<li><a href="${pageContext.request.contextPath}/mapDemo.jsp">Map Demo</a></li>
							</ul>
						</li>

						<li><a href="${pageContext.request.contextPath}/aboutProject.jsp">About This Project</a></li>

					</ul>

				 </div>
			</div>
			</div>

		</nav>
