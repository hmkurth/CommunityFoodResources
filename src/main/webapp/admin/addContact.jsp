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
    <h3  class="text-center pad-bottom-sm">${message}</h3>
    <h4 class="text-center pad-bottom-sm">Almost Done!  Add Contact/s for this resource </h4>


    <form action="${pageContext.request.contextPath}/addContact"  method="post" autocomplete="on">

        <div class="form-group">
            <label for="contact">Contact Details(optional field to connect a food resource with a point of contact)
                <!-- //TODO drop down menu of resource contacts set in the servlet
                    //https://www.codejava.net/java-ee/jsp/how-to-create-dynamic-drop-down-list-in-jsp-from-database -->
                <select name="contact" id="contact">
                    <!-- option to add new first, then if yes, another menu for adding owner should appear-->
                    <option value='9999'>Add New Contact</option>
                    <option value='8888'>There is no specific conact</option>
                    <c:forEach items="${listContact}" var="contact">
                        <option value='${contact.id}'<c:if test="${contact.id eq selectedContactId}">selected="selected"</c:if>
                        > contact name:${contact.firstName}, ${contact.lastName}</option>
                    </c:forEach>
                </select>

            </label>

            <input type="submit" class="btn btn-primary btn-lg"  name="submit" value="Next">

        </div>
    </form>


    <c:if test="${param.contact == '9999'}"  >
    <h3>Add a new contact for this resource</h3>
    <form action="${pageContext.request.contextPath}/addContact"  method="post" autocomplete="on">

        <div class="form-group">
            <label for="firstName">First Name (opt)
                <input type="text" class="form-control" id="firstName" name ="firstName" placeholder="first name"  >
            </label>
            <label for="lastName">Last Name or Org name(required)
                <input type="text" class="form-control" id="lastName" name ="lastName" placeholder="last name"  aria-required="true" required >
            </label>

            <label for="website">Email Address or Website (optional)
                <input type="text" class="form-control" id="website" name ="website" placeholder="website for the org/owner"  aria-required="false"  >
            </label>

            <label for="phone">Phone Number (optional)
                <input type="text" class="form-control" id="phone" name ="phone" placeholder="phone number"  aria-required="false"  >
            </label>


            <input type="submit" class="btn btn-primary btn-lg"  name="submit2" value="Next">
        </div>

        </c:if>














</div>
<jsp:include page="../footer.jsp"/>