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



        <!--TODO form verification! auto complete attribute -->

        <form action="${pageContext.request.contextPath}/deleteUser"  method="post" autocomplete="on">
            <div class="form-group">
                <label for="delete">Enter a User Id to Delete</label>
                <input type="text" class="form-control" id="delete" name ="delete" placeholder="id to delete"  required>
            </div>


            <hr />

            <button type="submit" name="submit" class="btn btn-primary btn-lg" value="Submit">Submit</button>
        </form>

<!-- if submit was pushed, display message only show confirm delete if submit is not null -->
        <c:if test="${req.getParameter('submit') != null}" >
            <h1>${message}</h1>
            <form action="${pageContext.request.contextPath}/deleteUser"  method="post" autocomplete="on">

                <input type="button" name=confirmDelete" class="btn btn-primary btn-lg">Confirm Delete</input>
            </form>

            </div>
            </div>
        </c:if>


</div>
<jsp:include page="../footer.jsp" />

