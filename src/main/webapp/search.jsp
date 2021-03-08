<%--
  Created by IntelliJ IDEA.
  User: heath
  Date: 3/7/2021
  Time: 9:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="head.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Users</title>
</head>
<body>
<h2>User Display Exercise</h2>

<a href = "searchUser">Go to the User Search</a>
<h1>Search By Last Name</h1>
<form action="/searchUser" method="get">
    <label for="searchTerm">Please Enter A last name
        <input type="text" name="searchTerm" id="searchTerm"/>
    </label>
    <label for="searchProperty">Please Enter A property to search by
        <input type="text" name="searchProperty" id="searchProperty"/>
    </label>
    <input type="submit" name="" value="Enter" />
</form>
</body>
</html>
