<%@include file="/taglib.jsp"%>
<jsp:include page="/head.jsp"/>
<title>Search By Location</title>
<jsp:include page="/header.jsp"/>

<div class="banner">
    <div class="bread-wrap">
        <div class="container no-height">
            <div class="row">
                <div class="col-sm-12">
                    <ol class="breadcrumb">
                        <li>You are here:</li>
                        <li><a href="index.jsp">Home</a></li>
                        <li>Search By Location</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Begin main page content -->

<h2>Location Search Results: </h2>
<table class="table">
    <thead>
    <tr>
    <th>Name derived from location id/resource name</th>
    <th>Address</th>
    </tr>
    </thead>

    <c:forEach var="location" items="${nearLocations}">
        <tr>
            <td>${location.nameDesc}</td>
            <td>${location.streetAddressOrIntersection}</td>
            <td></td>

        </tr>

    </c:forEach>
</table><div class="row pad-bottom">

    </div>






        <div class="row pad-bottom">
            <div class="col-md-10 col-md-offset-1">

                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>


        </div>

    </div>
</div>


<jsp:include page="/footer.jsp"/>