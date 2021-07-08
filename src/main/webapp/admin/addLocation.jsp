<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../taglib.jsp" />
<jsp:include page="../head.jsp" />
<jsp:include page="../header.jsp"/>

<div class="login-container">
    <h3 class = "row text-center warning pad-bottom-sm">This resources has not yet been added, we just need a little more information</h3>
    <h3 class = "row text-center pad-bottom-sm">${message} </h3>

    <form action="${pageContext.request.contextPath}/addLocation"  method="post" autocomplete="on">
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
            <label for="nameDesc">Location Name or quick description if applicable (required)</label>
            <input type="text" class="form-control" id="nameDesc" name ="nameDesc" placeholder="Location Name"  aria-required="true" required>
        </div>
        <div class="form-group">
            <label for="streetAddressOrIntersection">Street Address or Intersection (required)</label>
            <input type="text" class="form-control" id="streetAddressOrIntersection" name ="streetAddressOrIntersection" placeholder="500 Washington St." pattern="\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\.?" autocomplete="street" aria-required="true"  required>
        </div>

        <div class="form-group">
            <label for="city">City (required)</label>
            <input type="text" class="form-control" id="city" name ="city" placeholder="City"  pattern ="(?:[A-Z][a-z.-]+[ ]?)+" autocomplete="city" aria-required="true" required>
        </div>

        <div class="form-group">
            <label for="state">State (required)</label>
            <input type="text" class="form-control" id="state" name ="state" placeholder="state"  max-length="2" autocomplete="state" aria-required="true" required>
        </div>

        <div class="form-group">
            <label for="zip">Zipcode (required)</label>
            <input type="text" class="form-control" id="zip" name ="zip" placeholder="zip" pattern="\b\d{5}(?:-\d{4})?\b" autocomplete="zip" aria-required="true" required>
        </div>
        <div class="form-group">
            <label for="busInfo">Bus Route Info (optional)</label>
            <input type="text" class="form-control" id="busInfo" name ="busInfo" placeholder="optional"  >
        </div>
        <div class="form-group">
            <label for="comments">Comments about this location (optional)</label>
            <input type="text" class="form-control" id="comments" name ="comments" placeholder="optional"  >
        </div>


        <hr />
        <button type="submit" name ="submit2" value = "Add Location" class="btn btn-primary btn-lg">Submit</button>
    </form>

</c:if>


</div>
<jsp:include page="../footer.jsp"/>