<%--
  Created by IntelliJ IDEA.
  User: heath
  Date: 5/6/2021
  Time: 10:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>

<jsp:include page="../head.jsp"/>
<jsp:include page="../header.jsp"/>
<title>Delete User Confirmation</title>

<!-- if submit was pushed, display message only show confirm delete if submit is not null -->

    <h3>${message}</h3>

    <form action="${pageContext.request.contextPath}/deleteUserAction"  method="post" autocomplete="on">

        <input type="submit" name="confirmDelete" value = "confirmDelete" class="btn btn-primary btn-lg">
    </form>


<jsp:include page="../footer.jsp"/>