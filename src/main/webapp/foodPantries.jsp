<%@include file="taglib.jsp"%>
<jsp:include page="/head.jsp"/>
<title>Food Pantries</title>
<jsp:include page="/header.jsp"/>

<div class="content" id="main_content">
    <div class="container">
        <div class="row pad-bottom">
            <div class="table-sm">
                <form action="${pageContext.request.contextPath}/displayDetails"  method="get" autocomplete="on">
                <h2>All Food Resources</h2>

                <table class="table table-striped table-bordered table-hover table-condensed table-sm">
                    <thead>
                    <tr>
                        <th>Type of Resource</th>
                        <th>Resource Name</th>
                        <th>Description</th>
                        <th>Location (optional) </th>
                        <th>Website</th>
                        <th>Click for full listing of details and map</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="resource" items="${allPantries}" varStatus="iter">

                    <tr>
                        <td>${resource.typeId.name}</td>
                        <td>${resource.name}</td>
                        <td>${resource.description}</td>
                        <td>${resource.location.nameDesc}, ${resource.location.streetAddressOrIntersection}, ${resource.location.city}${resource.location.lat}${resource.location.lng}</td>
                        <td><a href="${resource.website}">${resource.website}</a></td>
                        <td><button type="submit" class="btn btn-success"  name="details" value=${resource.id}>Details</button></td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </form>
                </div>
            </div>
        </div>
    <div id="map"></div>

    <jsp:include page="/map.jsp"/>
    <jsp:include page="/footer.jsp"/>


