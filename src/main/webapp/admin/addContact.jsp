<%--
  Created by IntelliJ IDEA.
  User: heath
  Date: 6/24/2021
  Time: 7:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<jsp:include page="../head.jsp"/>
<title>Add Contact</title>
<jsp:include page="../header.jsp"/>



<div class="login-container">
    <h2  class="text-center pad-bottom-sm">${message}</h2>
    <h1 class="text-center pad-bottom-sm">Add Contact/s for this resource </h1>


    <form action="${pageContext.request.contextPath}/addContact"  method="post" autocomplete="on">

        <div class="form-group">
            <label for="owner">Contact Details(optional field to connect a food resource with a point of contact)
                <!-- //TODO drop down menu of resource owners set in the servlet
                    //https://www.codejava.net/java-ee/jsp/how-to-create-dynamic-drop-down-list-in-jsp-from-database -->
                <select name="owner" id="owner">
                    <!-- option to add new first, then if yes, another menu for adding owner should appear-->
                    <option value='9999'>Add New Owner</option>
                    <option value='8888'>There is no specific owner or the owner wishes to remain private</option>
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