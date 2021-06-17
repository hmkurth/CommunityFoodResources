<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp"/>
<title>Add Resource Owner</title>
<jsp:include page="../header.jsp"/>



<div class="login-container">
    <h1 class="text-center pad-bottom-sm">Add Resource Owner </h1>


    <form action="${pageContext.request.contextPath}/addResourceOwner"  method="post" autocomplete="on">

        <div class="form-group">
            <label for="owner">Resource Owner(optional field to connect different food resources with a common owner)
                <!-- //TODO drop down menu of resource owners set in the servlet
                    //https://www.codejava.net/java-ee/jsp/how-to-create-dynamic-drop-down-list-in-jsp-from-database -->
                <select name="owner" id="owner">
                    <!-- option to add new first, then if yes, another menu for adding owner should appear-->
                    <option value='9999'>Add New Owner</option>
                    <option value='8888'>There is no specific owner</option>
                    <c:forEach items="${listOwner}" var="owner">
                        <option value='${owner.id}'<c:if test="${owner.id eq selectedOwnerId}">selected="selected"</c:if>
                        > owner name:${owner.name}</option>
                    </c:forEach>
                </select>

            </label>

            <input type="submit" class="btn btn-primary btn-lg"  name="submit" value="Next">

        </div>
    </form>


        <c:if test="${param.owner == '9999'}"  >
        <h3>Add a new owner for this resource</h3>
        <form action="${pageContext.request.contextPath}/addResourceOwner"  method="post" autocomplete="on">

            <div class="form-group">
                <label for="ownerName">Owner/Organization's Name (required)
                    <input type="text" class="form-control" id="ownerName" name ="ownerName" placeholder="name of the owner/org"  aria-required="true" required >
                </label>

                <label for="website">Website (optional)
                    <input type="text" class="form-control" id="website" name ="website" placeholder="website for the org/owner"  aria-required="false"  >
                </label>


                <input type="submit" class="btn btn-primary btn-lg"  name="submit2" value="Next">
            </div>

        </c:if>














</div>
<jsp:include page="../footer.jsp"/>