<%@ page import="java.util.Enumeration" %>
<%@include file="/taglib.jsp"%>


<%@include file="taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=0.80">

    <!-- Descriptive meta should this go here in the head, or in individual page heads?
   // <title>Accessible - Home</title>
    <meta name="title" content="max 65 character title">-->
    <meta name="description" content="this is the home page of the community food resource locator app">
    <meta name="keywords" content="comma, separated, list">
    <meta name="author" content="heather kurth">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${title}</title>

    <!-- Latest compiled and minified CSS -->
    <!-- CSS assets -->
    <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/font-awesome.css" rel="stylesheet">

    <!-- JS assets -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap-hover-dropdown.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.smooth-scroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.vide.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.bgswitcher.js"></script>

    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/assets/js/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- Theme specific assets -->
    <link href="${pageContext.request.contextPath}/assets/css/app.css" rel="stylesheet"><!-- Global portal stying -->
    <link href="${pageContext.request.contextPath}/assets/css/banners.css" rel="stylesheet"><!-- Page banners -->
    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
    <script>
        let map;
        // Initialize and add the map
        function initMap() {
            // The location of Madison
            const madison = { lat: 43.0731, lng:  -89.4012};
            // The map, centered at Madison
            const map = new google.maps.Map(document.getElementById("map"), {
                zoom: 9,
                center: madison,
            });
            // Create a <script> tag and set the geodata as the source.
            const script = document.createElement("script");
            script.src =
                "/resources/geoData.json";
            document.getElementsByTagName("head")[0].appendChild(script);
        }

        // Loop through the results array and place a marker for each
        // set of coordinates.
        const eqfeed_callback = function (results) {
            for (let i = 0; i < results.features.length; i++) {
                const coords = results.features[i].geometry.coordinates;
                const latLng = new google.maps.LatLng(coords[1], coords[0]);
                new google.maps.Marker({
                    position: latLng,
                    map: map,
                });
            }
        };


            /* The marker, positioned at Madison
            const marker = new google.maps.Marker({
                position: madison,
                map: map,
            });
/*/
            // NOTE: This uses cross-domain XHR, and may not work on older browsers.
           // map.data.loadGeoJson(
              //  "https://storage.googleapis.com/mapsdevsite/json/google.json"
           // );

    </script>
</head>
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


        <h3>If there is a valid location for this resource,a marker will appear the map will appear below</h3>
        <!--The div element for the map -->
        <div id="map"></div>

        <!-- Async script executes immediately and must be after any DOM elements used in callback. -->
        <script
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCLGoKo1ZhK7TyAsvpPwQZmlLsAQxWnpRM&callback=initMap&libraries=&v=weekly"
                async
        ></script>
    </div>
</div>