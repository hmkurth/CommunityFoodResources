<%@include file="taglib.jsp"%>
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
<div class="content" id="main_content">
    <div class="container">

        <div class="row text-center pad-top pad-bottom line-bottom">
            <div class="col-md-12">
                <h1>Enter a location to search nearby resources </h1>
                <form class="form-inline pad-top-sm" action="${pageContext.request.contextPath}/searchByLocation" method="get">

                    <div class="form-group">
                        <label for="streetAddressOrIntersection">Street Address or Intersection</label>
                        <input type="text" class="form-control" id="streetAddressOrIntersection" name ="streetAddressOrIntersection" placeholder="500 Washington St." autocomplete="street" aria-required="true"  required>
                    </div>

                    <div class="form-group">
                        <label for="city">City</label>
                        <input type="text" class="form-control" id="city" name ="city" placeholder="Madison" autocomplete="city" aria-required="true" required>
                    </div>

                    <div class="form-group">
                        <label for="state">State</label>
                        <input type="text" class="form-control" id="state" name ="state" placeholder="Wisconsin" autocomplete="state" aria-required="true" required>
                    </div>

                    <div class="form-group">
                        <label for="zip">Zipcode</label>
                        <input type="text" class="form-control" id="zip" name ="zip" placeholder="zip"  autocomplete="email" aria-required="true" required>
                    </div>

                    <button type="submit"name=submit"  class="btn btn-primary btn-lg">Search Site</button>
                </form>
            </div>
        </div>

        <jsp:include page="/map.jsp"/>

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