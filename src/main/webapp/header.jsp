<%@include file="taglib.jsp"%>

<body>
		<!-- Main navigation element for site, html5 elements used to improve rendering on assistive devices -->
		<nav class="navbar navbar-default navbar-fixed-top navbar-dark">
			<div class="top-header">
				<div class="container">

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
									<img src="${pageContext.request.contextPath}/assets/img/logos/sunLiteLogoRound.png" class="img-circle img-responsive"
										 alt="Back to home" />
								</a>

					 </div>


				<!-- Main site header -->
				<div class="collapse navbar-collapse " id="top_main_navigation">
				<ul class="nav navbar-nav ">
						<li><a href="${pageContext.request.contextPath}/forwardResources">Find Food Resources</a></li>
						<li><a href="${pageContext.request.contextPath}/addResource">Add A Community Food Resource</a></li>
						<li><a href="${pageContext.request.contextPath}/aboutProject.jsp">About This Project</a></li>

					</ul>

				 </div>
			</div>

		</nav>
