<%@include file="taglib.jsp"%>
<jsp:include page="/head.jsp"/>
<title>404 Page Not Found</title>

<jsp:include page="/header.jsp"/>


<!-- Begin main page content -->
<div class="content" id="main_content">
    <div class="container">

        <div class="row text-center pad-top pad-bottom">
            <div class="col-md-12">
                <h1>Not Found</h1>
                <p class="lead">Unfortunately, we can not locate the page you selected.</p>
                <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary btn-lg">Return Home</a>
            </div>
        </div>


    </div>
</div>
<jsp:include page="/footer.jsp"/>