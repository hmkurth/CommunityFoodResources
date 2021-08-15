
<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp" />
<title>Delete Resource</title>

<jsp:include page="../header.jsp" />
<!-- Begin main page content -->
<div class="content" id="main_content">
    <div class="container">
    <h4>${message}</h4>


    <div class="login-container">
        <h3 class="text-center pad-bottom-sm">Delete this Resource:</h3>
        <div class="row pad-bottom">

            <h2>Resource Details</h2>
            <table class="table table-responsive table-striped table-bordered">
                <thead>
                <tr>
                    <th>Type of Resource</th>
                    <th>Resource Name</th>
                    <th>Description</th>
                    <c:if test="${not empty resourceToEdit.comments}">
                        <th>Comments</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${resourceToEdit.typeId.name}</td>
                    <td>${resourceToEdit.name}</td>
                    <td>${resourceToEdit.description}</td>
                    <c:if test="${not empty resourceToEdit.comments}">
                        <td>Comments</td>
                    </c:if>
                </tr>
                </tbody>
            </table>
        </div>

<!-- if submit was  NOT pushed, display form-->
<c:if test="${req.getAttribute('submitted') != true}" >
    <form action="${pageContext.request.contextPath}/deleteResource"  method="post" autocomplete="on">
        <input type="submit" name="confirmDelete" value = "confirmDelete" class="btn btn-primary btn-lg">
    </form>
</c:if>
    </div>
</div>
