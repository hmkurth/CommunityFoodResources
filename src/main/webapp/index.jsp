<%@include file="taglib.jsp"%>
<jsp:include page="head.jsp" />
<title>Home</title>
<!-- import files should have "are relative references to a file in the same directory as the JSP." -->
<jsp:include page="header.jsp" />
<jsp:include page="banner.jsp" />
<!--show admin controls if logged in user is admin
<c:if test="${pageContext.request.isUserInRole('admin')}">
    <jsp:include page="admin/adminHome.jsp" />
</c:if>
<jsp:include page="indexMain.jsp" />
<jsp:include page="footer.jsp" />


