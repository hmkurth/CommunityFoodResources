<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp" />
<title>Admin Home</title>

<jsp:include page="../header.jsp" />

<!-- Image row NEW MARKUP -->
<div class="container pad-top">
<h3>${message}</h3>
<div class="row text-center">


    <div class="col-sm-3">
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

    <div class="col-sm-3">
        <a href="${pageContext.request.contextPath}/addResource" class="no-hover fancy-hover">
            <div class="row mobile-left">
                <div class="col-xs-5 col-sm-12">
                    <img src ="${pageContext.request.contextPath}/assets/img/demo/FLP_milwaukee_lansing3.jpg"  alt="placeholder image" class="img-responsive img-circle img-pad" />
                </div>
                <div class="col-xs-7 col-sm-12">
                    <h4>Add a new food resource</h4>
                    <p>Add a new resource to the database, we will want information on the details, location, and contacts, if applicable, for the resource</p>
                </div>
            </div>
        </a>
    </div>
<hr class="visible-xs col-xs-12" />

    <div class="col-sm-3">
        <a href="${pageContext.request.contextPath}/editResource" class="no-hover fancy-hover">
            <div class="row mobile-left">
                <div class="col-xs-5 col-sm-12">
                    <img src="${pageContext.request.contextPath}/assets/img/demo/FLP_milwaukee_lansing.jpg" alt="placeholder image" class="img-responsive img-circle img-pad" />
                </div>
                <div class="col-xs-7 col-sm-12">
                    <h4>Edit Existing food resource</h4>

                </div>
            </div>
        </a>
    </div>
    <hr class="visible-xs col-xs-12" />


    <div class="col-sm-3">
    <a href="${pageContext.request.contextPath}/verifyResources" class="no-hover fancy-hover">
        <div class="row mobile-left">
            <div class="col-xs-5 col-sm-12">
                <img src="${pageContext.request.contextPath}/assets/img/demo/madisonCapitol.jpg" alt="Madison Capitol" class="img-responsive img-circle img-pad"
                    />
            </div>
            <div class="col-xs-7 col-sm-12">
                <h4>Verify Resources</h4>
                <c:if test="${numberToVerify gt 0}" >
                <h5>You have  ${numberToVerify} resource/s to verify</h5>
                </c:if>
                <c:if test="${numberToVerify lt 1 }" >
                    <h5>No new resources to verify</h5>
                </c:if>
                <p></p>
            </div>
        </div>
    </a>
</div>
</div>
</div>
