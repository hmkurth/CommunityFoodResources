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
                        <c:if test="${not empty (resourcesAll.comments )}">
                            <th>Comments</th>
                        </c:if>
                        <c:if test="${not empty resourcesAll.location}">
                            <th>Location </th>
                        </c:if>
                        <c:if test="${not empty resourcesAll.serviceArea}">
                            <th>Service Area</th>
                        </c:if>
                        <c:if test="${not empty resourcesAll.owner}">
                            <th>Owner</th>
                        </c:if>
                        <c:if test="${not empty resourcesAll.daysOfWeek}">
                            <th>Days of Week</th>
                        </c:if>
                        <c:if test="${not empty resourcesAll.deliveryOffered}">
                            <th>Delivery Offered?</th>
                        </c:if>
                        <c:if test="${not empty resourcesAll.deliveryDescription}">
                            <th>Delivery Description</th>
                        </c:if>
                        <c:if test="${not empty resourcesAll.dietaryConsiderations}">
                            <th>Dietary Considerations</th>
                        </c:if>

                        <c:if test="${not empty resourcesAll.contactId}">
                            <th>Contact</th>
                        </c:if>
                        <c:if test="${not empty resourcesAll.website}">
                            <th>Website</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>

                    <tr>
<c:forEach var="resource" items="${resourcesAll}" varStatus="iter">
                        <td>${resourcesAll.typeId.name}</td>
                        <td>${resourcesAll.name}</td>
                        <td>${resourcesAll.description}</td>
                        <c:if test="${not empty resourcesAll.comments }">
                            <td>${resourcesAll.comments}</td>
                        </c:if>
                        <c:if test="${not empty resourcesAll.location}">
                            <td>${resourcesAll.location}</td>
                        </c:if>
                        <c:if test="${not empty resourcesAll.serviceArea}">
                            <td>optional${resourcesAll.serviceArea}</td>
                        </c:if>
                        <c:if test="${not empty resourcesAll.owner}">
                            <td>${resourcesAll.owner}</td>
                        </c:if>
                        <c:if test="${not empty resourcesAll.daysOfWeek}">
                            <td>${resourcesAll.daysOfWeek}</td>
                        </c:if>
                        <c:if test="${not empty resourcesAll.deliveryOffered}">
                            <td>${resourcesAll.deliveryOffered}</td>
                        </c:if>
                        <c:if test="${not empty resourcesAll.deliveryDescription}">
                            <td>${resourcesAll.resourcesAll.deliveryDescription}</td>
                        </c:if>
                        <c:if test="${not empty resourcesAll.dietaryConsiderations}">
                            <td>${resourcesAll.dietaryConsiderations}</td>
                        </c:if>

                        <c:if test="${not empty resourcesAll.contactId}">
                            <td>${resourcesAll.contactId}</td>
                        </c:if>
                        <c:if test="${not empty resourcesAll.website}">
                            <td>${resourcesAll.website}</td>
                        </c:if>

                    </tr>
                    </c:forEach>

                    </tbody>
                </table>

            </div>

        </div>

            <jsp:include page="/map.jsp"/>
            <jsp:include page="/footer.jsp"/>


