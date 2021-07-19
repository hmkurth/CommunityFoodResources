<%@ page import="java.util.Enumeration" %>
<%@include file="/taglib.jsp"%>
<jsp:include page="/head.jsp"/>
<title>Display Details</title>
<jsp:include page="/header.jsp"/>




<div class="row pad-bottom">

    <div class="col-md-10 col-md-offset-1">

        <h2>Resource Details</h2>
<table class="table table-responsive table-striped table-bordered">
    <thead>
    <tr>
        <th>Type of Resource</th>
        <th>Resource Name</th>
        <th>Description</th>
        <c:if test="${not empty toDisplay.comments}">
            <th>Comments</th>
        </c:if>
        <c:if test="${not empty toDisplay.location}">
            <th>Location </th>
        </c:if>
        <c:if test="${not empty toDisplay.serviceArea}">
            <th>Service Area</th>
        </c:if>
        <c:if test="${not empty toDisplay.owner}">
            <th>Owner</th>
        </c:if>
        <c:if test="${not empty toDisplay.daysOfWeek}">
            <th>Days of Week</th>
        </c:if>
        <c:if test="${not empty toDisplay.deliveryOffered}">
            <th>Delivery Offered?</th>
        </c:if>
        <c:if test="${not empty toDisplay.deliveryDescription}">
            <th>Delivery Description</th>
        </c:if>
        <c:if test="${not empty toDisplay.dietaryConsiderations}">
            <th>Dietary Considerations</th>
        </c:if>

        <c:if test="${not empty toDisplay.contactId}">
            <th>Contact</th>
        </c:if>
        <c:if test="${not empty toDisplay.website}">
            <th>Website</th>
        </c:if>
    </tr>
    </thead>
    <tbody>

    <tr>

        <td>${toDisplay.typeId.name}</td>
        <td>${toDisplay.name}</td>
        <td>${toDisplay.description}</td>
        <c:if test="${not empty toDisplay.comments}">
            <td>Comments</td>
        </c:if>
        <c:if test="${not empty toDisplay.location}">
            <td>${toDisplay.location}</td>
        </c:if>
        <c:if test="${not empty toDisplay.serviceArea}">
            <td>optional: ${toDisplay.serviceArea}</td>
        </c:if>
        <c:if test="${not empty toDisplay.owner}">
            <td>${toDisplay.owner}</td>
        </c:if>
        <c:if test="${not empty toDisplay.daysOfWeek}">
            <td>${toDisplay.daysOfWeek}</td>
        </c:if>
        <c:if test="${not empty toDisplay.deliveryOffered}">
            <td>${toDisplay.deliveryOffered}</td>
        </c:if>
        <c:if test="${not empty toDisplay.deliveryDescription}">
            <td>${toDisplay.deliveryDescription}</td>
        </c:if>
        <c:if test="${not empty toDisplay.dietaryConsiderations}">
            <td>${toDisplay.dietaryConsiderations}</td>
        </c:if>

        <c:if test="${not empty toDisplay.contactId}">
            <td>${toDisplay.contactId}</td>
        </c:if>
        <c:if test="${not empty toDisplay.website}">
            <td>${toDisplay.website}</td>
        </c:if>
    </tr>

    </tbody>
</table>


        <h3>If there is a valid location for this resource, the map will appear below</h3>
        <!--The div element for the map -->
        <div id="map"></div>

        <!-- Async script executes immediately and must be after any DOM elements used in callback. -->
        <script
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCLGoKo1ZhK7TyAsvpPwQZmlLsAQxWnpRM&callback=initMap&libraries=&v=weekly"
                async
        ></script>
    </div>
</div>