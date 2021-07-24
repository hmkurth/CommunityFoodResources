
<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp"/>
<title>Add or Edit a Resource</title>

<jsp:include page="../header.jsp"/>

<div class="login-container">
    <h1 class="text-center pad-bottom-sm">Add a new resource</h1>

    <form action="${pageContext.request.contextPath}/addResource"  method="post" autocomplete="on">
        <div class="form-group">
            <label for="name">Resource Name</label>
            <input type="text" class="form-control" id="name" name ="name" placeholder="Give this resource a name"  aria-required="true" required value="${newResource.name}">


        </div>

        <div class="form-group">
            <label for="type">Resource Type, please select the most accurate category for this resource, or select other
                <select name="type" id="type">

                    <c:forEach items="${listType}" var="type">
                        <option value="${type.id}">type name: ${type.name}</option>
                    </c:forEach>
                </select>
            </label>
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" name ="description" aria-required="true" required maxlength="500" placeholder="Provide a short description(max 500 characters)"><c:out value="${newResource.description}"/></textarea>
        </div>

        <div class="form-group">
            <label for="serviceArea">Service Area(optional/if known)</label>
            <textarea class="form-control" id="serviceArea" name ="serviceArea" aria-required="false"  maxlength="500"   placeholder="Is there a specific area(city, county, state) that this resource is limited to serving?  Note that you will be able to add the specific location of the resource, if applicable, later "><c:out value="${newResource.serviceArea}"/></textarea>
        </div>

        <div class="form-group">
            <label for="website">Website Link (optional)</label>
            <input type="text" class="form-control" id="website" name ="website" placeholder="enter website"  aria-required="false"  value="${newResource.website}" >
        </div>

        <div class="form-group">
            <label for="documentation">What Documentation is needed? (Required)</label>
            <textarea class="form-control" id="documentation" name ="documentation" aria-required="true"  maxlength="500"  required placeholder="What documentation is needed to access this resource? SSN? ID? None? unknown?"><c:out value="${newResource.documentation}"/></textarea>
        </div>

        <div class="form-group">
            <label for="days">Days of Week Offered(optional)</label>
            <textarea class="form-control" id="days" name ="days" aria-required="false"  maxlength="100"  placeholder="Are there specific days of the week this resource is offered? Please enter the full day name separated by commas if needed" ><c:out value="${newResource.daysOfWeek}"/></textarea>
        </div>

        <div class="form-group">
            <label for="deliveryB">Does this resource offer delivery?
            <input type="radio" class="form-control" id="deliveryB" name ="deliveryB" aria-required="true"  value="false" checked="checked"  >No  <br/>
            <input type="radio" class="form-control"  name ="deliveryB" aria-required="true"  value="true"   >Yes
            </label>
        </div>

        <div class="form-group">
            <label for="deliveryDescription">Details of delivery service if offered?(optional)</label>
            <textarea class="form-control" id="deliveryDescription" name ="deliveryDescription" aria-required="false"  maxlength="500"  placeholder="Specifics of delivery service, hours, areas, etc" ><c:out value="${newResource.deliveryDescription}"/></textarea>
        </div>

        <div class="form-group">
            <label for="dietary">Dietary Considerations?(optional)</label>
            <textarea class="form-control" id="dietary" name ="dietary" aria-required="false"  maxlength="500" placeholder="Are there dietary considerations provided by this service? i.e. able to provide food for people with allergies, gluten intolerance, diabetics, etc?(max 500 characters)" ><c:out value="${newResource.dietaryConsiderations}"/></textarea>
        </div>


        <div class="form-group">
            <label for="comments">Comments(optional)</label>
            <textarea class="form-control" id="comments" name ="comments" aria-required="false"  maxlength="500" placeholder="Is there anything else people should know about this resource?(max 500 characters)" ><c:out value="${newResource.comments}"/></textarea>
        </div>

        <hr />
        <h3>Next you be given the option to add a location, a resource owner, and contacts for this resource</h3>
        <input type="submit" class="btn btn-primary btn-lg"  name="submit" value="confirm">
    </form>

<c:if test="${param.submit == true}"  >
    <p><a href="/addResourceOwner" class="btn btn-primary btn-lg">Add an owner to this resource</a>
    <a href="/addContact" class="btn btn-primary btn-lg">Add contact details for this resource</a>>
    <a href="/addLocation" class="btn btn-primary btn-lg">Add location for this resource</a> </p>

</c:if>


</div>
<jsp:include page="../footer.jsp"/>