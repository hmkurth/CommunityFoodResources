<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../taglib.jsp" />
<jsp:include page="../head.jsp" />
<jsp:include page="../header.jsp"/>

<h3 class = "row text-center warning">This resources has not yet been added, we just need a little more information</h3>
<h3 class = "row text-center">${message} </h3>

<div class="container pad">

    <form action="${pageContext.request.contextPath}/addLocation"  method="post" autocomplete="on">
        <div class="form-group">
            <label  for="nextOptions">What would you like to do next?
                <select name="nextOptions" id="nextOptions">
                    <option value='noLocation'>There is no specific location for this resource, go to add contacts</option>
                    <option value='addLocation'>Add A Location</option>

                </select>

            </label>

            <input type="submit" class="btn btn-primary btn-lg"  name="submit" value="Next">

        </div>
    </form>
</div>
<c:if test="${param.nextOptions.equals('addLocation')}"  >
    <form action="${pageContext.request.contextPath}/addLocation"  method="post" autocomplete="on">
        <!--get the food resource to add location to -->
        <div class="form-group">
            <label for="resourceId">Food Resource Name</label>
            <input type="text" class="form-control" id="resourceId" name ="resourceId" placeholder="Food Resource"  aria-required="true" required>
        </div>


        <div class="form-group">
            <label for="nameDesc">Location Name</label>
            <input type="text" class="form-control" id="nameDesc" name ="nameDesc" placeholder="Location Name"  aria-required="true" required>
        </div>
        <div class="form-group">
            <label for="streetAddressOrIntersection">Street Address or Intersection</label>
            <input type="text" class="form-control" id="streetAddressOrIntersection" name ="streetAddressOrIntersection" placeholder="500 Washington St." autocomplete="street" aria-required="true"  required>
        </div>

        <div class="form-group">
            <label for="city">City</label>
            <input type="text" class="form-control" id="city" name ="city" placeholder="Madison" autocomplete="city" aria-required="true" required>
        </div>

        <div class="form-group">
            <label for="state">State</label>
            <input type="text" class="form-control" id="state" name ="state" placeholder="Wisconsin" autocomplete="state" aria-required="true" required>
        </div>

        <div class="form-group">
            <label for="zip">Zipcode</label>
            <input type="text" class="form-control" id="zip" name ="zip" placeholder="zip"  autocomplete="email" aria-required="true" required>
        </div>
        <div class="form-group">
            <label for="busInfo">Bus Route Info</label>
            <input type="text" class="form-control" id="busInfo" name ="busInfo" placeholder="optional"  >
        </div>
        <div class="form-group">
            <label for="comments">Comments about this location</label>
            <input type="text" class="form-control" id="comments" name ="comments" placeholder="optional"  >
        </div>


        <hr />
        <button type="submit" name ="submit2" value = "Add Location" class="btn btn-primary btn-lg">Submit</button>
    </form>

</c:if>


</div>
<jsp:include page="../footer.jsp"/>