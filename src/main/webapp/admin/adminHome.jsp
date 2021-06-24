<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp" />
<title>Admin Home</title>

<jsp:include page="../header.jsp" />

<!-- Image row NEW MARKUP -->
<div class="container">
<h3>${message}</h3>
<div class="row text-center">


    <div class="col-sm-4">
        <a href="${pageContext.request.contextPath}/deleteUser" class="no-hover fancy-hover">
            <div class="row mobile-left">
                <div class="col-xs-5 col-sm-12">
                    <img src="${pageContext.request.contextPath}/assets/img/demo/FLP_marshal.jpg" alt="placeholder image" class="img-responsive img-circle img-pad" />
                </div>
                <div class="col-xs-7 col-sm-12">
                    <h4>Delete User</h4>
                    <p>See list of users and choose id to delete</p>
                </div>
            </div>
        </a>
    </div>

<hr class="visible-xs col-xs-12" />

    <div class="col-sm-4">
        <a href="${pageContext.request.contextPath}/addResource" class="no-hover fancy-hover">
            <div class="row mobile-left">
                <div class="col-xs-5 col-sm-12">
                    <img src="${pageContext.request.contextPath}/assets/img/demo/FLP_milwaukee_lansing.jpg" alt="placeholder image" class="img-responsive img-circle img-pad" />
                </div>
                <div class="col-xs-7 col-sm-12">
                    <h4>Add a new food resource</h4>
                    <p>Add a new resource to the database, you will then have the option to add a location, if applicable, for the resource</p>
                </div>
            </div>
        </a>
    </div>
<hr class="visible-xs col-xs-12" />

<div class="col-sm-4">
    <a href="${pageContext.request.contextPath}/admin/addLocation.jsp" class="no-hover fancy-hover">
        <div class="row mobile-left">
            <div class="col-xs-5 col-sm-12">
                <img src="${pageContext.request.contextPath}/assets/img/demo/madisonCapitol.jpg" alt="placeholder image" class="img-responsive img-circle img-pad"
                    />
            </div>
            <div class="col-xs-7 col-sm-12">
                <h4>Add a Location</h4>
                <p></p>
            </div>
        </div>
    </a>
</div>
</div>
</div>
