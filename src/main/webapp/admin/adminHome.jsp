<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp" />
<title>Admin Home</title>

<jsp:include page="../header.jsp" />
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Food Resources<span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li><a href="${pageContext.request.contextPath}/admin/addLocation.jsp">Add Location</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/addResource.jsp">Add Food Resource(not hooked up</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/deleteUser.jsp">Delete User(Not hooked up</a></li>

    </ul>
</li>


<!-- Image row NEW MARKUP -->
<div class="row text-center">

    <hr class="visible-xs col-xs-12" />

    <div class="col-sm-4">
        <a href="${pageContext.request.contextPath}/admin/addLocation.jsp" class="no-hover fancy-hover">
            <div class="row mobile-left">
                <div class="col-xs-5 col-sm-12">
                    <img src="${pageContext.request.contextPath}/assets/img/demo/madisonCapitol.jpg" alt="placeholder image" class="img-responsive img-circle img-pad"
                         width="1536" height="2048"/>
                </div>
                <div class="col-xs-7 col-sm-12">
                    <h4>Add a Location</h4>
                    <p></p>
                </div>
            </div>
        </a>
    </div>

    <hr class="visible-xs col-xs-12" />

    <div class="col-sm-4">
        <a href="${pageContext.request.contextPath}/admin/addResource.jsp" class="no-hover fancy-hover">
            <div class="row mobile-left">
                <div class="col-xs-5 col-sm-12">
                    <img src="${pageContext.request.contextPath}/assets/img/demo/FLP_milwaukee_lansing.jpg" alt="placeholder image" class="img-responsive img-circle img-pad" />
                </div>
                <div class="col-xs-7 col-sm-12">
                    <h4>Add a new food resource</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit</p>
                </div>
            </div>
        </a>
    </div>

    <hr class="visible-xs col-xs-12" />

    <div class="col-sm-4">
        <a href="${pageContext.request.contextPath}/admin/deleteUser.jsp" class="no-hover fancy-hover">
            <div class="row mobile-left">
                <div class="col-xs-5 col-sm-12">
                    <img src="${pageContext.request.contextPath}/assets/img/demo/FLP_marshal.jpg" alt="placeholder image" class="img-responsive img-circle img-pad" />
                </div>
                <div class="col-xs-7 col-sm-12">
                    <h4>Delete User</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit</p>
                </div>
            </div>
        </a>
    </div>
</div>



<jsp:include page="../footer.jsp" />

