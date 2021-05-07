<%@include file="taglib.jsp"%>
<jsp:include page="/head.jsp"/>
<title>Food Pantries</title>

<jsp:include page="/header.jsp"/>

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

                        <th>Days Of Week Offered</th>
                        <th>Delivery Offered?</th>
                        <th>Dietary Considerations?</th>
                        <th>Contact</th>
                        <th>Website</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="resource" items="${resourcesAll}" varStatus="iter">
                        <tr>

                            <td>${resource.type_id.name}</td>
                            <td>${resource.name}</td>
                            <td>${resource.description}</td>
                            <td>${resource.comments}</td>
                            <td>${resource.location.nameDesc}</td>
                            <td>${resource.serviceArea}</td>

                            <td>${resource.daysOfWeek}</td>
                            <td>${resource.deliveryOffered}</td>
                            <td>${resource.dietaryConsiderations}</td>
                            <td>${resource.contactId}</td>
                            <td>${resource.website}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>



        </div>
            <jsp:include page="/footer.jsp"/>


