<%--
  Created by IntelliJ IDEA.
  User: heath
  Date: 6/16/2021
  Time: 12:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../taglib.jsp"%>

<jsp:include page="../head.jsp"/>
<jsp:include page="../header.jsp"/>
<title>Add Qwner Success</title>
<!--TODO show user the added location and resource, maybe confirm? -->
<body>
<h1>owner added success
    ${successMessage}</h1>

</body>

<jsp:include page="../footer.jsp"/>