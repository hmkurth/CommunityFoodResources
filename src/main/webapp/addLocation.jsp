<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="taglib.jsp" />
<jsp:include page="head.jsp" />
<jsp:include page="header.jsp"/>
<!-- Begin main page content -->
<div class="content" id="main_content">

<div class="login-container">
    <h3>Location Details</h3>
    <h5 class = "row text-center pad-bottom-sm">${message} </h5>
    <h4 class = "row text-center warning pad-bottom-sm">This resource has not yet been added, we just need a little more information</h4>

    <form action="${pageContext.request.contextPath}/addLocation"  method="get" autocomplete="on">
        <div class="form-group">
            <label  for="nextOptions">What would you like to do next?
                <select name="nextOptions" id="nextOptions">
                    <option value='addNewLocation'>Add A Location</option>
                    <option value='editLocation'><c:if test="${ location != null}">Edit this existing location: ${location.nameDesc}</c:if></option>
                    <option value='noLocation'>There is no specific location for this resource, go to add contacts</option>
                    <c:forEach items="${sessionScope.allLocations}" var="location">
                        <option value='${location.id}'<c:if test="${location.id eq selectedLocationId}">selected="selected"</c:if>
                        > add this location :${location.nameDesc}</option>
                    </c:forEach>

                </select>
            </label>
            <input type="submit" class="btn btn-primary btn-lg"  name="submit" value="Next">
        </div>
    </form>
</div>


<c:if test="${param.nextOptions.equals('addNewLocation') or param.nextOptions.equals('editLocation')}"  >
    <div class="login-container">
            <h3>Add or edit location to food resource: ${newResource.name}, please use your best guess if you don't know exact details, as this information will be used to map the location for searches</h3>

        <div class="login-container">
        <form action="${pageContext.request.contextPath}/addLocation"  method="post" autocomplete="on">
        <div class="form-group">
            <label for="nameDesc">Location Name or quick description if applicable (required)</label>
            <input type="text" class="form-control" id="nameDesc" name ="nameDesc" placeholder="Location Name" value="${location.nameDesc}" aria-required="true" required>
        </div>


        <div class="form-group">
            <label for="streetAddressOrIntersection">Street Address or Intersection (required)</label>
            <input type="text" class="form-control" id="streetAddressOrIntersection" name ="streetAddressOrIntersection" placeholder="500 Washington St."  autocomplete="street" aria-required="true"  value="${location.streetAddressOrIntersection}"required>
        </div>

        <div class="form-group">
            <label for="city">City (required)</label>
            <input type="text" class="form-control" id="city" name ="city" placeholder="City"  pattern ="(?:[A-Z][a-z.-]+[ ]?)+" autocomplete="city" aria-required="true" value ="${location.city}"  required>
        </div>

        <div class="form-group">
            <label for="state">State (required)</label>
            <input type="text" class="form-control" id="state" name ="state" placeholder="state"  max-length="2" autocomplete="state" aria-required="true" value="${location.state}" required>
        </div>

        <div class="form-group">
            <label for="zip">Zipcode (required)</label>
            <input type="text" class="form-control" id="zip" name ="zip" placeholder="zip" pattern="\b\d{5}(?:-\d{4})?\b" autocomplete="zip" aria-required="true" value="${location.zip}"required>
        </div>
        <div class="form-group">
            <label for="busInfo">Bus Route Info (optional)</label>
            <input type="text" class="form-control" id="busInfo" name ="busInfo" placeholder="optional" value="${location.busInfo}" >
        </div>
        <div class="form-group">
            <label for="comments">Comments about this location (optional)</label>
            <input type="text" class="form-control" id="comments" name ="comments" value="${location.comments}" placeholder="optional"  >
        </div>


        <hr />
            <input type ="submit" name ="submit2" value = "Add/Update Location" class="btn btn-primary btn-lg">
    </form>

</c:if>
</div>

</div>
<jsp:include page="footer.jsp"/>