
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<div class="banner">
    <div class="bread-wrap">
        <div class="container no-height">
            <div class="row">
                <div class="col-sm-12">
                    <ol class="breadcrumb">
                        <li>You are here:</li>
                        <li><a href="index.jsp">Home</a></li>
                        <li>Search Results</li>
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
                <form class="form-inline pad-top-sm" action="${pageContext.request.contextPath}/searchUser"method="get">
                    <div class="form-group">
                        <label for="type">Search For:</label>
                        <input type="text"name="type" id="type" class="form-control input-lg" placeholder="type" />
                    </div>
                    <div class="form-group">
                        <label for="location"> &nbsp; Search In:</label>
                        <select class="form-control input-lg" id="location" name="location">
                            <option>All</option>
                            <option>Food Pantries</option>
                            <option>Free Little Pantries</option>
                            <option>Government Resources</option>
                            <option>Community Aid and Support </option>
                        </select>
                    </div>
                    <button type="submit"name=submit"  class="btn btn-primary btn-lg">Search Site</button>
                </form>
            </div>
        </div>

        <div class="row pad-bottom">
            <div class="col-md-10 col-md-offset-1">
                <h2>All Food Resources</h2>
                <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Type of Resource</th>
                    <th>Resource Name</th>
                    <th>Description</th>
                    <th>Comments</th>
                    <th>Location</th>
                    <th>Service Area</th>
                    <th>Documentation Needed?</th>
                    <th>Days Of Week Offered</th>
                    <th>Delivery Offered?</th>
                    <th>Dietary Considerations?</th>
                    <th>Contact</th>
                    <th>Website</th>
                </tr>
                </thead>
                <tbody>
        <c:forEach var="resource" items="${resourcesAll}">
                <tr>

                    <td>${resource.type}</td>
                    <td>${resource.name}</td>
                    <td>${resource.desc}</td>
                    <td>${resource.comments}</td>
                    <td>${resource.location}</td>
                    <td>${resource.serviceArea}</td>
                    <td>${resource.documention}</td>
                    <td>${resource.daysOfWeek}</td>
                    <td>${resource.deliveryOffered}</td>
                    <td>${resource.dietaryConsiderations}</td>
                    <td>${resource.contact}</td>
                    <td>${resource.website}</td>
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

