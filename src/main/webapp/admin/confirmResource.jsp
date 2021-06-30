<%@ page import="java.util.Enumeration" %>
<%@include file="../taglib.jsp"%>
    <jsp:include page="../head.jsp"/>
    <title>Add Resource Success</title>
    <!--TODO show user the added location and resource, maybe confirm? -->
    <jsp:include page="../header.jsp"/>

<h3>${message}</h3>
    <h2>You have successfully added a new resource, please confirm the details</h2>

<div class="row pad-bottom">
    <div class="col-md-10 col-md-offset-1">
        <h2>Resource Details</h2>
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Type of Resource</th>
                <th>Resource Name</th>
                <th>Description</th>
                <th>Comments</th>
                <th>Location</th>
                <th>Service Area</th>
                <th>Owner</th>
                <th>Days Of Week Offered</th>
                <th>Delivery Offered?</th>
                <th>Dietary Considerations?</th>
                <th>Contact</th>
                <th>Website</th>
            </tr>
            </thead>
            <tbody>

                <tr>

                    <td>${newResource.typeId.name}</td>
                    <td>${newResource.name}</td>
                    <td>${newResource.description}</td>
                    <td>${newResource.comments}</td>
                    <td>${newResource.location.nameDesc}</td>
                    <td>${newResource.serviceArea}</td>
                    <td>${newResource.owner.name}</td>

                    <td>${newResource.daysOfWeek}</td>
                    <td>${newResource.deliveryOffered}</td>
                    <td>${newResource.dietaryConsiderations}</td>
                    <td>${newResource.contactId}</td>
                    <td>${newResource.website}</td>
                </tr>

            </tbody>
        </table>








        <!--  https://www.tutorialspoint.com/jsp/jsp_form_processing.htm
         <h2>HTTP Header Request Example</h2>
         <table width = "100%" border = "1" align = "center">
             <tr bgcolor = "#949494">
                 <th>Param Name</th>
                 <th>Param Value(s)</th>
             </tr>
             <%
                 Enumeration paramNames = request.getParameterNames();
                 while(paramNames.hasMoreElements()) {
                     String paramName = (String)paramNames.nextElement();
                     out.print("<tr><td>" + paramName + "</td>\n");
                     String paramValue = request.getHeader(paramName);
                     out.println("<td> " + paramValue + "</td></tr>\n");
                 }
             %>
         </table>
     -->

    <jsp:include page="../footer.jsp"/>