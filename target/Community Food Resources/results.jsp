
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

</body>
</html>
