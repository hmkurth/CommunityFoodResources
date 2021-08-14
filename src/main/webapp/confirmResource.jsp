<%@ page import="java.util.Enumeration" %>
<%@include file="taglib.jsp"%>
    <jsp:include page="head.jsp"/>
    <title>Add Resource Success</title>
    <jsp:include page="header.jsp"/>




<div class="row pad-bottom">

    <div class="col-md-10 col-md-offset-1">
        <h3>${message}</h3>
    <h2>Please confirm the details of this resource</h2>


        <h2>Resource Details</h2>
        <table class="table table-responsive table-striped table-bordered">
            <thead>
            <tr>
                <th>Type of Resource</th>
                <th>Resource Name</th>
                <th>Description</th>
                <c:if test="${not empty newResource.comments}">
                    <th>Comments</th>
                </c:if>
                <c:if test="${not empty newResource.location}">
                    <th>Location </th>
                </c:if>
                <c:if test="${not empty newResource.serviceArea}">
                    <th>Service Area</th>
                </c:if>
                <c:if test="${not empty newResource.owner}">
                    <th>Owner</th>
                </c:if>
                <c:if test="${not empty newResource.daysOfWeek}">
                    <th>Days of Week</th>
                </c:if>
                <c:if test="${not empty newResource.deliveryOffered}">
                    <th>Delivery Offered?</th>
                </c:if>
                <c:if test="${not empty newResource.deliveryDescription}">
                    <th>Delivery Description</th>
                </c:if>
                <c:if test="${not empty newResource.dietaryConsiderations}">
                    <th>Dietary Considerations</th>
                </c:if>

                <c:if test="${not empty newResource.contactId}">
                    <th>Contact</th>
                </c:if>
                <c:if test="${not empty newResource.website}">
                    <th>Website</th>
                </c:if>
            </tr>
            </thead>
            <tbody>

                <tr>

                    <td>${newResource.typeId.name}</td>
                    <td>${newResource.name}</td>
                    <td>${newResource.description}</td>
                    <c:if test="${not empty newResource.comments}">
                        <td>Comments</td>
                    </c:if>
                    <c:if test="${not empty newResource.location}">
                        <td>${newResource.location}</td>
                    </c:if>
                    <c:if test="${not empty newResource.serviceArea}">
                        <td>optional: ${newResource.serviceArea}</td>
                    </c:if>
                    <c:if test="${not empty newResource.owner}">
                    <td>${newResource.owner}</td>
                    </c:if>
                    <c:if test="${not empty newResource.daysOfWeek}">
                    <td>${newResource.daysOfWeek}</td>
                    </c:if>
                    <c:if test="${not empty newResource.deliveryOffered}">
                    <td>${newResource.deliveryOffered}</td>
                    </c:if>
                    <c:if test="${not empty newResource.deliveryDescription}">
                        <td>${newResource.deliveryDescription}</td>
                    </c:if>
                    <c:if test="${not empty newResource.dietaryConsiderations}">
                    <td>${newResource.dietaryConsiderations}</td>
                    </c:if>

                    <c:if test="${not empty newResource.contactId}">
                    <td>${newResource.contactId}</td>
                    </c:if>
                    <c:if test="${not empty newResource.website}">
                    <td>${newResource.website}</td>
                    </c:if>
                </tr>

            </tbody>
        </table>

        <form action="${pageContext.request.contextPath}/confirmResource"  method="post" autocomplete="on">
            <div class="form-group">
                <label  for="confirmAdd">Choose From the following options
                    <select name="confirmAdd" id="confirmAdd">
                        <option value='addData'>Ready To Add To The Database</option>
                        <option value='addLocation'>Add or Edit A Location</option>
                        <option value='addContact'>Add or Edit A Contact</option>
                        <option value='addResourceOwner'>Add or Edit A Resource Owner</option>
                        <option value='addResource'>Edit Other Details of This resource(this will bring you back to the start of submission )</option>
                    </select>
                </label>
                <input type="submit" class="btn btn-primary btn-lg"  name="submit" value="Next">
            </div>
        </form>

    </div>
</div>






    <jsp:include page="footer.jsp"/>