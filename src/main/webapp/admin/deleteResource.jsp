
<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp" />
<title>Delete Resource</title>

<jsp:include page="../header.jsp" />

<div class="container">
    <h3>${message}</h3>


    <div class="login-container">
        <h1 class="text-center pad-bottom-sm">Delete this Resource: ${resourceToEdit}</h1>
        <div class="row pad-bottom">

            <h2>Resource Details</h2>
            <table class="table table-responsive table-striped table-bordered">
                <thead>
                <tr>
                    <th>Type of Resource</th>
                    <th>Resource Name</th>
                    <th>Description</th>
                    <c:if test="${not empty resourceToEdit.comments}">
                        <th>Comments</th>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.location}">
                        <th>Location </th>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.serviceArea}">
                        <th>Service Area</th>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.owner}">
                        <th>Owner</th>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.daysOfWeek}">
                        <th>Days of Week</th>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.deliveryOffered}">
                        <th>Delivery Offered?</th>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.deliveryDescription}">
                        <th>Delivery Description</th>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.dietaryConsiderations}">
                        <th>Dietary Considerations</th>
                    </c:if>

                    <c:if test="${not empty resourceToEdit.contactId}">
                        <th>Contact</th>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.website}">
                        <th>Website</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>

                <tr>

                    <td>${resourceToEdit.typeId.name}</td>
                    <td>${resourceToEdit.name}</td>
                    <td>${resourceToEdit.description}</td>
                    <c:if test="${not empty resourceToEdit.comments}">
                        <td>Comments</td>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.location}">
                        <td>${resourceToEdit.location}</td>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.serviceArea}">
                        <td>optional${resourceToEdit.serviceArea}</td>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.owner}">
                        <td>${resourceToEdit.owner}</td>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.daysOfWeek}">
                        <td>${resourceToEdit.daysOfWeek}</td>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.deliveryOffered}">
                        <td>${resourceToEdit.deliveryOffered}</td>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.deliveryDescription}">
                        <td>${resourceToEdit.deliveryDescription}</td>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.dietaryConsiderations}">
                        <td>${resourceToEdit.dietaryConsiderations}</td>
                    </c:if>

                    <c:if test="${not empty resourceToEdit.contactId}">
                        <td>${resourceToEdit.contactId}</td>
                    </c:if>
                    <c:if test="${not empty resourceToEdit.website}">
                        <td>${resourceToEdit.website}</td>
                    </c:if>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
<!-- if submit was  NOT pushed, display form-->
<c:if test="${req.getAttribute('submitted') != true}" >
    <form action="${pageContext.request.contextPath}/deleteResource"  method="post" autocomplete="on">

        <input type="submit" name="confirmDelete" value = "confirmDelete" class="btn btn-primary btn-lg">
    </form>
</c:if>