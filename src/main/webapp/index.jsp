<%@include file="taglib.jsp"%>
<c:import url="/head.jsp" />
<title>Home</title>

<c:import url="/header.jsp" />
<c:import url="/banner.jsp" />
<!--show admin controls if logged in user is admin
<c:if test="${pageContext.request.isUserInRole('admin')}">
    <c:import url="/admin/adminHome.jsp" />
</c:if>

<c:import url="/indexMain.jsp" />
<c:import url="/footer.jsp" />


