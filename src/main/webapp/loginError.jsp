<<%@include file="taglib.jsp"%>
<jsp:include page="/head.jsp"/>
<title>Login Error</title>

<jsp:include page="/header.jsp"/>

<div class="col-md-4 col-md-offset-1">
    <h3>>There was an error logging you in</h3>


    <a href="${pageContext.request.contextPath}/loginAction" class="btn btn-primary btn-lg">Try Again</a>
</div>


<jsp:include page="/footer.jsp"/>