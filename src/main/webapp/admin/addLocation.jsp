<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../taglib.jsp" />
<jsp:include page="../head.jsp" />
<jsp:include page="../header.jsp"/>

<div class="login-container">
    <h4 class = "row text-center warning pad-bottom-sm">This resource has not yet been added, we just need a little more information</h4>
    <h5 class = "row text-center pad-bottom-sm">${message} </h5>

    <form action="${pageContext.request.contextPath}/addLocation"  method="get" autocomplete="on">
        <div class="form-group">
            <label  for="nextOptions">What would you like to do next?
                <select name="nextOptions" id="nextOptions">
                    <option value='addLocation'>Add A Location</option>
                    <option value='noLocation'>There is no specific location for this resource, go to add contacts</option>
                </select>
            </label>
            <input type="submit" class="btn btn-primary btn-lg"  name="submit" value="Next">
        </div>
    </form>
</div>
<c:if test="${param.nextOptions.equals('addLocation')}"  >
    <div class="login-container">
    <form action="${pageContext.request.contextPath}/addLocation"  method="post" autocomplete="on">
        <!--get the food resource to add location to -->

            <h3>Add location to food resource: ${newResource.name}, please use your best guess if you don't know exact details, as this information will be used to map the location for searches</h3>
        <div class="form-group">
            <label  for="chooseLocation">Choose New Location or an existing location
                <select name="chooseLocation" id="chooseLocation">
                    <option value='9999'>Add A new Location</option>
                    <c:forEach items="${allLocations}" var="location">
                        <option value='${location.id}'<c:if test="${location.id eq selectedLocationId}">selected="selected"</c:if>
                        > location name:${location.nameDesc}</option>
                    </c:forEach>
                </select>
            </label>
            <input type="submit" class="btn btn-primary btn-lg"  name="submitLocation" value="Next">
        </div>
    </form>
</c:if>

    <c:if test="${param.chooseLocation == '9999'}"  >
        <div class="login-container">
        <form action="${pageContext.request.contextPath}/addLocation"  method="post" autocomplete="on">
        <div class="form-group">
            <label for="nameDesc">Location Name or quick description if applicable (required)</label>
            <input type="text" class="form-control" id="nameDesc" name ="nameDesc" placeholder="Location Name" <c:out value="${location.nameDesc}"/> aria-required="true" required>
        </div>


        <div class="form-group">
            <label for="streetAddressOrIntersection">Street Address or Intersection (required)</label>
            <input type="text" class="form-control" id="streetAddressOrIntersection" name ="streetAddressOrIntersection" placeholder="500 Washington St." pattern="\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St|Court|Heights)\.?" autocomplete="street" aria-required="true" <c:out value="${location.streetAddressOrIntersection}"/> required>
        </div>

        <div class="form-group">
            <label for="city">City (required)</label>
            <input type="text" class="form-control" id="city" name ="city" placeholder="City"  pattern ="(?:[A-Z][a-z.-]+[ ]?)+" autocomplete="city" aria-required="true"  <c:out value="${location.city}"/> required>
        </div>

        <div class="form-group">
            <label for="state">State (required)</label>
            <input type="text" class="form-control" id="state" name ="state" placeholder="state"  max-length="2" autocomplete="state" aria-required="true" <c:out value="${location.state}"/>required>
        </div>

        <div class="form-group">
            <label for="zip">Zipcode (required)</label>
            <input type="text" class="form-control" id="zip" name ="zip" placeholder="zip" pattern="\b\d{5}(?:-\d{4})?\b" autocomplete="zip" aria-required="true" <c:out value="${location.zip}"/>required>
        </div>
        <div class="form-group">
            <label for="busInfo">Bus Route Info (optional)</label>
            <input type="text" class="form-control" id="busInfo" name ="busInfo" placeholder="optional" <c:out value="${location.busInfo}"/>  >
        </div>
        <div class="form-group">
            <label for="comments">Comments about this location (optional)</label>
            <input type="text" class="form-control" id="comments" name ="comments" <c:out value="${location.comments}"/>placeholder="optional"  >
        </div>


        <hr />
        <button type="submit" name ="submit2" value = "Add Location" class="btn btn-primary btn-lg">Submit</button>
    </form>

</c:if>
</div>

</div>
<jsp:include page="../footer.jsp"/>