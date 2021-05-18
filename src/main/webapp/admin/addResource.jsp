
<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp"/>
<title>Add Resource</title>

<jsp:include page="../header.jsp"/>

<div class="login-container">
    <h1 class="text-center pad-bottom-sm">Add A New Resource</h1>


    <!--TODO form verification! auto complete attribute
     Note that the value for the attribute items must match the name of the corresponding attribute set in the servlet class.
     As you can see, the values of the drop down list are the IDs of the categories.-->

    <form action="${pageContext.request.contextPath}/addResource"  method="post" autocomplete="on">
        <div class="form-group">
            <label for="name">Resource Name</label>
            <input type="text" class="form-control" id="name" name ="name" placeholder="Give this resource a name"  aria-required="true" required <c:out value="${resourceName}" /> >
            <!-- TODO if/when form is resubmitted, try to keep the data that the user entered  -->

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
            <textarea class="form-control" id="description" name ="description" aria-required="true" required maxlength="500" >Provide a short description(max 500 characters)</textarea>
        </div>

        <div class="form-group">
            <label for="serviceArea">Service Area(if known)</label>
            <textarea class="form-control" id="serviceArea" name ="serviceArea" aria-required="false"  maxlength="500" >Is there a specific area(city, county, state) that this resource is limited to serving?  Note that you will be able to add the specific location of the resource, if applicable, later </textarea>
        </div>

        <div class="form-group">
            <label for="website">Website Link (optional)</label>
            <input type="text" class="form-control" id="website" name ="website" placeholder="enter website"  aria-required="false" pattern="^((https?|ftp|smtp):\/\/)?(www.)?[a-z0-9]+\.[a-z]+(\/[a-zA-Z0-9#]+\/?)*$">
        </div>
]
        <div class="form-group">
            <label for="documentation">Documentation</label>
            <textarea class="form-control" id="documentation" name ="comments" aria-required="true"  maxlength="500"  required>What documentation is needed to access this resource? SSN? ID? None? unknown?
                </textarea>
        </div>

        <div class="form-group">
            <label for="days">Days of Week Offered(optional)</label>
            <textarea class="form-control" id="days" name ="days" aria-required="false"  maxlength="100"  >Are there specific days of the week this resource is offered? Please enter the full day name separated by commas if needed
                </textarea>
        </div>


        <div class="form-group">
            <label for="comments">Comments(optional)</label>
            <textarea class="form-control" id="comments" name ="comments" aria-required="false"  maxlength="500" >Is there anything else people should know about this resource?(max 500 characters)(max 500 characters)</textarea>
        </div>



        <div class="form-group">
            <label for="owner">Resource Owner(optional field to connect different food resources with a common owner)
            <!-- //TODO drop down menu of resource owners set in the servlet
                //https://www.codejava.net/java-ee/jsp/how-to-create-dynamic-drop-down-list-in-jsp-from-database -->
            <select name="owner" id="owner">
                <!-- option to add new first, then if yes, another menu for adding owner should appear-->
                <option value="9999">Add New Owner</option>
                <c:forEach items="${listOwner}" var="owner">
                    <option value="${owner.id}" <c:if test="${owner.id eq selectedOwnerId}">selected="selected"</c:if>
                    > owner name:${owner.name}</option>
                </c:forEach>
            </select>
            </label>


        </div>

        <c:if test="${param.owner == 9999}"  >
            <h3> new owner fields here </h3>

        </c:if>


        <hr />
        <button type="submit" class="btn btn-primary btn-lg">Submit</button>
    </form>




</div>
<jsp:include page="../footer.jsp"/>