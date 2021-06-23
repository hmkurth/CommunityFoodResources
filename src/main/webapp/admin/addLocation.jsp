<jsp:include page="../taglib.jsp" />
<jsp:include page="../head.jsp" />
<jsp:include page="../header.jsp"/>


<h2>${message} </h2>
<h3>Would You like to add a location for this resource?</h3>
//yes or no, if yes, show form, if, no, move on to ask about contacts
<div class="login-bg">
<!--TODO form verification!  -->

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
        <button type="submit" class="btn btn-primary btn-lg">Submit</button>
    </form>




</div>
<jsp:include page="../footer.jsp"/>