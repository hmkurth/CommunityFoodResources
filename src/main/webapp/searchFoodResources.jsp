
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/head.jsp"/>
<title>Search Food Resources</title>

<jsp:include page="/header.jsp"/>




<div class="banner">
    <div class="bread-wrap">
        <div class="container no-height">
            <div class="row">
                <div class="col-sm-12">
                    <ol class="breadcrumb">
                        <li>You are here:</li>
                        <li><a href="index.jsp">Home</a></li>
                        <li>Search Food Resources</li>
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
                <h1>Search Our Site </h1>
                <form class="form-inline pad-top-sm" action="${pageContext.request.contextPath}/searchFoodResources" method="get">
                    <div class="form-group">
                        <label for="term">Search For:</label>
                        <input type="text" name="term" id="term" class="form-control input-lg" placeholder="search term" />
                    </div>
                    <div class="form-group">
                        <label for="categories"> &nbsp; Search Categories:</label>
                        <select class="form-control input-lg" id="categories" name="categories">
                            <option>All</option>
                            <option>Food Pantries</option>
                            <option>Free Little Pantries</option>
                            <option>Government Resources</option>
                            <option>Community Aid and Support </option>
                        </select>
                    </div>
                    <button type="submit" name=submit" value="search" class="btn btn-primary btn-lg">Search Site</button>
                </form>
            </div>
        </div>


        <!-- search results TODO break out into another jsp, figure out how to display the most relevant info, provide link to the full resource listing -->
        <div class="row pad-bottom">
            <div class="col-md-10 col-md-offset-1">
                <h2>Search Results</h2>
                <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Type of Resource</th>
                    <th>Resource Name</th>

                </tr>
                </thead>
                <tbody>
        <c:forEach var="resource" items="${resources}">
                <tr>

                    <td>${resource.type}</td>
                    <td>${resource.name}</td>

                    </tr>
        </c:forEach>
                </tbody>
                </table>



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
