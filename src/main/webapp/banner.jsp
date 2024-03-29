<%@include file="/taglib.jsp"%>

<!--Banner-->
<div class="banner home-1"  data-type="background" data-speed="4">
    <div class="container short">
        <div class="banner-interior">

            <div class="row">
                <div class="col-md-5 " >
                    <h2>Welcome To Madison Food Resources</h2>
                    <h3>A guide to community and public pantries, programs, and other food aid resources</h3>

                    <!--check if user is logged in before displaying -->
                    <c:if test="${ empty loggedInUser}">
                        <p class="pad-top"><a href="userSignup.jsp" class="btn btn-primary btn-lg">Sign Up</a> <a href="loginAction" class="btn btn-default btn-lg">Sign In</a></p>
                    </c:if>
                </div>

                <div class="col-md-6 ">
                    <img src="${pageContext.request.contextPath}/assets/img/logos/sunLogo.png" alt="madison food resources logo" class="fa-align-center img img-responsive img-circle" />
                </div>
            </div>

        </div>


        </div>
    </div>

