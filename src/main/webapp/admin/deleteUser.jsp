
<%@include file="/taglib.jsp"%>
<jsp:include page="../head.jsp"/>
<title>Delete User</title>

<jsp:include page="../header.jsp"/>


<div class="login-container">
<h1 class="text-center pad-bottom-sm">Delete a User</h1>
<div class="row pad-bottom">



<div class="col-md-10 col-md-offset-1">
    <h2>All Users</h2>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>
            <th>ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${AllUsers}" varStatus="iter">
            <tr>

                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.userName}</td>
                <td>${user.id}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
    <!-- if submit was  NOT pushed, display form-->
    <c:if test="${req.getAttribute('submitted') != true}" >
        <form action="${pageContext.request.contextPath}/deleteResource"  method="post" autocomplete="on">

            <input type="submit" name="confirmDelete" value = "confirmDelete" class="btn btn-primary btn-lg">
        </form>
</c:if>
<h2 class="warning">${errorMessage}</h2>

<!-- if submit was pushed, display message only show confirm delete if submit is not null-->
<c:if test="${param.submit != null}" >
     <h1>${message}</h1>

    <form action="${pageContext.request.contextPath}/deleteUserAction"  method="post" autocomplete="on">

        <input type="submit" name="confirmDelete" value = "Confirm" class="btn btn-primary btn-lg">

        <input type="reset" name="reset" value = "Reset" class="btn btn-primary btn-lg">
    </form>

</c:if>


</div>
</div>
<jsp:include page="../footer.jsp"/>