<%@include file="/taglib.jsp"%>

<!--Banner-->
<div class="banner home-1"  data-type="background" data-speed="4">
    <div class="banner-interior">
        <div class="container short">
            <div class="row">
                <div class="col-md-10 col-md-offset-1 text-center">
                    <h1>Welcome To Madison Food Resources</h1>
                    <!--TODO check if user is logged in before displaying -->
<c:if test="${ empty loggedInUser}">
                    <p><a href="userSignup.jsp" class="btn btn-primary btn-lg">Sign Up</a> <a href="loginAction" class="btn btn-default btn-lg">Sign In</a></p>
</c:if>
<c:if test="${  req.isUserInRole('admin')}">
                    <a style="color: white" href="${pageContext.request.contextPath}/admin/adminHome.jsp">Admin Functions(remove)</a>
</c:if>
    </div>
            </div>
        </div>
    </div>
</div>
