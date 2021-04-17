
<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Results" />
<%@include file="head.jsp"%>
<html><body>

<%--Pretty up the results!--%>
<div class="container-fluid">
    <h2>Search Results From Last Name: </h2>
    <table class="table">
        <thead>
        <th>Full Name</th>
        <th>User Name</th>
        <th></th>
        </thead>
        </tr>
        <c:forEach var="user" items="${searchResult}">
            <tr>
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.userName}</td>
                <td></td>

            </tr>

        </c:forEach>
    </table>

<!--
    <h3>All Users</h3>

    <table class="table">
        <thead>s
        <th>Full Name</th>
        <th>User Name</th>
        <th>Age</th>
        </thead>
        </tr>
        <c:forEach var="employee" items="${users}">
            <tr>
                <td>${employee.firstName} ${employee.lastName}</td>
                <td>${employee.userName}</td>
                <td></td>

            </tr>

        </c:forEach>
    </table>
-->
</div>
<table class="table table-striped table-bordered">
    <thead>
    <tr>
        <th>#</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Username</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">1</th>
        <td>Mark</td>
        <td>Otto</td>
        <td>@cbc_one</td>
    </tr>
    <tr>
        <th scope="row">2</th>
        <td>Jacob</td>
        <td>Thornton</td>
        <td>@cbc_two</td>
    </tr>
    <tr>
        <th scope="row">3</th>
        <td>Larry</td>
        <td>the Bird</td>
        <td>@cbc_three</td>
    </tr>
    </tbody>
</table>
</body>
</html>
