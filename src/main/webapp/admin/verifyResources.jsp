<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp" />
<title>Verify Resources</title>

<jsp:include page="../header.jsp" />

<div class="content  pad-top" id="main_content">
    <div class="container">

<div class="row pad-bottom">

    <div class="col-md-10 col-md-offset-1">

        <h2>Please verify the details of this resource, at this time I have not introduced auto verification, so we want to check for duplicate entries and try to get the location detailed enough to map</h2>


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

            <c:forEach var="resource" items="${unverifiedResources}" varStatus="iter">
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
                        <option value='addResource'>Edit Other Details of This resource(this will bring you back to the start of submission )</option>
                    </select>
                </label>
                <input type="submit" class="btn btn-primary btn-lg"  name="submit" value="Verify">
            </div>
        </form>

    </div>
</div>


<jsp:include page="../footer.jsp"/>