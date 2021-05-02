<%@include file="/taglib.jsp"%>
<c:import url="/head.jsp" />
<title>Admin Home</title>

<c:import url="/header.jsp" />

    <h3>User Controls</h3>
    <p></p>
    <hr />
    <h3></h3>


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

        <form action="${pageContext.request.contextPath}/deleteUserAction"  method="post" autocomplete="on">
            <div class="form-group">
                <label for="delete">Enter a User Id to Delete</label>
                <input type="text" class="form-control" id="delete" name ="delete" placeholder="id to delete"  required>
            </div>


            <hr />
            <button type="submit" class="btn btn-primary btn-lg">Submit</button>
        </form>




    </div>
</div>
<c:import url="/footer.jsp" />

