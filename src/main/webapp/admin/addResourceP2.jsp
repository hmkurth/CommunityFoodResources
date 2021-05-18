
<%@include file="../taglib.jsp"%>

<title>Add Resource Location, Owner, and Contacts</title>



<div class="login-container">
    <h1 class="text-center pad-bottom-sm">Add Resource Location, Owner, and Contacts</h1>


    <form action="${pageContext.request.contextPath}/addResourceP2"  method="post" autocomplete="on">

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














</div>
<jsp:include page="../footer.jsp"/>