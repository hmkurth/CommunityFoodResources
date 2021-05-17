
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
            <input type="text" class="form-control" id="name" name ="name" placeholder="Give this resource a name"  aria-required="true" required>
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
            <label for="owner">Resource Owner(optional field to connect different food resources with a common owner)
            <!-- //TODO drop down menu of resource owners set in the servlet
                //https://www.codejava.net/java-ee/jsp/how-to-create-dynamic-drop-down-list-in-jsp-from-database -->
            <select name="owner" id="owner">
                <!-- option to add new first, then if yes, another menu for adding owner should appear-->
                <option value="newOwner">Add New Owner</option>
                <c:forEach items="${listOwner}" var="owner">
                    <option value="${owner.id}" <c:if test="${owner.id eq selectedOwnerId}">selected="selected"</c:if>
                    > owner name:${owner.name}</option>
                </c:forEach>
            </select>
            </label>


        </div>

        <c:if test="${req.getAttribute('selectedOwnerId') == ('newOwner')}"  >
            <h3> new owner fields here </h3>

        </c:if>


        <hr />
        <button type="submit" class="btn btn-primary btn-lg">Submit</button>
    </form>




</div>
<jsp:include page="../footer.jsp"/>