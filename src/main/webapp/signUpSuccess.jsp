<%@include file="taglib.jsp"%>
<c:import url="head.jsp" />
<title>Sign Up Success</title>

<c:import url="/header.jsp" />


<div class="content" id="signupSuccess">
    <div class="container">

        <div class="row pad-bottom">
            <div class="col-md-7">
                <img src="${pageContext.request.contextPath}/assets/img/demo/capitolFlowersHeart.jpg" alt="Madison capitol building with heart flower bed" class="img-responsive img-rounded" />
            </div>

            <div class="col-md-4 col-md-offset-1">
                <h3>Thank-you for signing up with Madison Food Resources</h3>
                <p class="lead">This is a work in progress, and your feedback and contributions are important!<br/>
                   Please contact me with any problems you have accessing these resources.<a href="mailto:hmkurth@madisoncollege.com">Contact Administrator</a><br/>
                    You can now login to begin exploring</p>

                <a href="index.jsp" class="btn btn-primary btn-lg">Back Home To Login </a>
            </div>
        </div>
    </div>
</div>



<c:import url="/footer.jsp" />