<%@ page import="java.util.Enumeration" %>
<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp" />
<title>Verify Resources</title>

<jsp:include page="../header.jsp" />

<div class="content  pad-top" id="main_content">
    <div class="container">

<div class="row pad-bottom">

    <div class="col-md-10 ">

        <p>Please verify the details of this resource, at this time I have not introduced auto verification, so we want to check for duplicate entries and try to get the location detailed enough to map</p>


        <h2>Resource Details</h2>
        <table class="table table-responsive table-striped table-bordered">
            <thead>
            <tr>
                <th>Type of Resource</th>
                <th>Resource Name</th>
                <th>Description</th>

                    <th>Comments</th>

                    <th>Location </th>

                    <th>Service Area</th>

                    <th>Owner</th>

                    <th>Days of Week</th>

                    <th>Delivery Offered?</th>

                    <th>Delivery Description</th>

                    <th>Dietary Considerations</th>

                    <th>Contact</th>

                    <th>Website</th>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="resource" items="${resourcesToVerify}" varStatus="iter">
                <tr>
                    <td>${resource.typeId.name}</td>
                    <td>${resource.name}</td>
                    <td>${resource.description}</td>
                    <td>${resource.comments}</td>

                    <td>${resource.location.nameDesc}, ${resource.location.streetAddressOrIntersection}, ${resource.location.city}</td>
                    <td>${resource.serviceArea}</td>
                    <td>${resource.owner}</td>
                    <td>${resource.daysOfWeek}</td>
                    <td>${resource.deliveryOffered}</td>
                    <td>${resource.deliveryDescription}</td>
                    <td>${resource.dietaryConsiderations}</td>
                    <td>${resource.contactId}</td>
                    <td><a href="${resource.website}">${resource.website}</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form action="${pageContext.request.contextPath}/verifyResources"  method="post" autocomplete="on">
            <div class="form-group">

                <label for="thisResource">Resource To Verify
                    <!-- //TODO drop down menu of resources that aren't verified -->
                    <select name="thisResource" id="thisResource">

                        <c:forEach items="${resourcesToVerify}" var="resource1">
                            <option value='${resource1.id}'<c:if test="${resource1.id eq selectedResourceId}">selected="selected"</c:if>
                            > resource name:${resource1.name}</option>
                        </c:forEach>
                    </select>

                </label>


                <label  for="confirmVerify">Choose From the following options
                    <select name="confirmVerify" id="confirmVerify">
                        <option value='addData'>Ready To Display</option>
                        <option value='addLocation'>Add or Edit A Location</option>
                        <option value='addContact'>Add or Edit A Contact</option>
                        <option value='addResourceOwner'>Add or Edit A Resource Owner</option>
                        <option value='editResource'>Edit Other Details of This resource(this will bring you back to the start of submission )</option>
                        <option value='deleteResource'>Delete This resource</option>
                    </select>
                </label>
                <input type="submit" class="btn btn-primary btn-lg"  name="submit" value="Next">
            </div>
        </form>

        <h3>My Google Maps Demo</h3>
        <!--The div element for the map -->
        <div id="map"></div>

        <!-- Async script executes immediately and must be after any DOM elements used in callback. -->
        <script
                Client client = ClientBuilder.newClient();
                WebTarget target =
                        client.target("https://maps.googleapis.com/maps/api/staticmap?center=43.0731,-89.4012&zoom=10&size=400x400&markers=my%20house%7C" + lat + ",%20" + lng + "&key=AIzaSyCLGoKo1ZhK7TyAsvpPwQZmlLsAQxWnpRM");
                async
        ></script>

    </div>
</div>


<jsp:include page="../footer.jsp"/>