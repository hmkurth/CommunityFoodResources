
<!-- Main navigation element for site, html5 elements used to improve rendering on assistive devices -->
<nav class="navbar navbar-default navbar-fixed-top navbar-white">
    <div class="top-header">
        <div class="container">

            <!-- Skip to content link allows users rendering the site on a screen reader to skip past navigational elements -->
            <div class="menu-block pull-left no-pad hidden-xs">
                <a href="indexMain.jsp" class="btn btn-link scroller"><em class="fa fa-level-down" aria-hidden="true"></em> Skip to Content &nbsp;</a>
            </div>

            <!-- This is optional, comment out if you do not need this set of dropdowns at the top -->
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

            <!-- TODO Enable the user to adjust the font size of the web page -->


            <div class="menu-block pull-left hidden-xs">Text Size:</div>
            <button type="button" class="btn btn-link pull-left hidden-xs increase-font" aria-label="Increase"><em class="fa fa-plus" aria-hidden="true"></em></button>
            <button type="button" class="btn btn-link pull-left hidden-xs decrease-font" aria-label="Decrease"><em class="fa fa-minus" aria-hidden="true"></em></button>


            <!-- Secondary navigation and login dropdown -->
            <div class="menu-block pull-right no-pad">
                <ul>
                    <!-- Example secondary links -->
                    <li><a href="#" class="btn btn-link">Link One</a></li>
                    <li><a href="#" class="btn btn-link">Link Two</a></li>
                    <li><a href="#" class="btn btn-link">Link Three</a></li>

                    <!-- Login dropdown -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle btn-login" data-toggle="dropdown"><em class="fa fa-lock" aria-hidden="true"></em>  Sign In</a>
                        <ul id="login-dp" class="dropdown-menu">
                            <li>
                                <div class="row">
                                    <div class="col-md-12">
                                        <form class="form" role="form" method="post" action="login" id="login-nav">
                                            <div class="form-group">
                                                <label for="loginUser">Username</label>
                                                <input type="text" class="form-control" id="loginUser" placeholder="Username" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="loginPassword">Password</label>
                                                <input type="password" class="form-control" id="loginPassword" placeholder="Password" required>
                                                <div class="help-block text-right"><a href="#">Forget password?</a></div>
                                            </div>
                                            <div class="form-group">
                                                <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="bottom text-center col-sm-12">
                                        <a href="#">Register new account</a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>

        </div>
    </div>

    <div class="container">

        <!-- Mobile hamburger style menu -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top_main_navigation" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">
                <img src="" alt="Back to home" />
            </a>
        </div>

        <!-- Main site header -->
        <div class="collapse navbar-collapse" id="top_main_navigation">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Home <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="index.html">Static Photo</a></li>
                        <li><a href="home2.html">Rotating Photo</a></li>
                        <li><a href="home3.html">Video Background</a></li>
                        <li><a href="home4.html">Coming Soon</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Pages <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="page1.html">General Content</a></li>
                        <li><a href="page2.html">Blog Main</a></li>
                        <li><a href="page3.html">Blog Article</a></li>
                        <li><a href="page4.html">Portfolio</a></li>
                        <li><a href="page5.html">Job Listing</a></li>
                        <li><a href="page6.html">Job Detail</a></li>
                        <li><a href="page7.html">Search Results</a></li>
                        <li><a href="page8.html">User Profile</a></li>
                        <li><a href="page9.html">Login</a></li>
                        <li><a href="page10.html">Sign-Up</a></li>
                        <li><a href="page11.html">404 Not Found</a></li>
                        <li><a href="page12.html">Legal</a></li>
                    </ul>
                </li>
                <li><a href="components.html">Components</a></li>
            </ul>

            <form class="navbar-form navbar-right hidden-sm hidden-md" action="page7.html">
                <div class="input-group">
                    <label for="search_main" class="hidden">Site Search</label>
                    <input type="text" class="form-control" id="search_main" placeholder="Search this site">
                    <span class="input-group-btn">
								<button class="btn btn-default" type="submit" aria-label="Search"><em class="fa fa-search" aria-hidden="true"></em></button>
							</span>
                </div>
            </form>
        </div>
    </div>
</nav>
<!--
<div class="banner" id="rotate-banner">
    <div class="banner-interior">
        <div class="container tall">
            <div class="row">
                <div class="col-md-10 col-md-offset-1 text-center">
                    <h1>Accessible Framework</h1>
                    <p class="lead">A simple theme for building WCAG compliant web experiences</p>
                    <p><a href="page1.html" class="btn btn-primary btn-lg">Page Templates</a> <a href="components.html" class="btn btn-default btn-lg">Components</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
-->