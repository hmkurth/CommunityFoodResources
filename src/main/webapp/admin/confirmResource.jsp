<%@ page import="java.util.Enumeration" %>
<%@include file="../taglib.jsp"%>
    <jsp:include page="../head.jsp"/>
    <title>Add Resource Success</title>
    <!--TODO show user the added location and resource, maybe confirm? -->
    <jsp:include page="../header.jsp"/>




<div class="container">
<h3>${message}</h3>
    <h2>You have successfully added a new resource, please confirm the details</h2>


        <h2>Resource Details</h2>
        <table class="table table-condensed table-striped table-bordered">
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
                    <td>optional${newResource.comments}</td>
                    <td>optional${newResource.location.nameDesc}, lattitude: ${newResource.location.lat} </td>
                    <td>optional${newResource.serviceArea}</td>
                    <td>optional${newResource.owner.name}</td>

                    <td>optional${newResource.daysOfWeek}</td>
                    <td>optional${newResource.deliveryOffered}</td>
                    <td>optional${newResource.dietaryConsiderations}</td>
                    <td>optional${newResource.contactId}</td>
                    <td>optional${newResource.website}</td>
                </tr>

            </tbody>
        </table>

        <form action="${pageContext.request.contextPath}/confirmResource"  method="post" autocomplete="on">
            <div class="form-group">
                <label  for="confirmAdd">Choose From the following options
                    <select name="confirmAdd" id="confirmAdd">
                        <option value='addData'>Ready To Add To The Database</option>
                        <option value='addLocation'>Add or Edit A Location</option>
                        <option value='addContact'>Add or Edit A Contact</option>
                        <option value='addResourceOwner'>Add or Edit A Resource Owner</option>
                        <option value='addResource'>Edit Other Details of This resource(this will bring you back to the start of submission )</option>
                    </select>
                </label>
                <input type="submit" class="btn btn-primary btn-lg"  name="submit" value="Next">
            </div>
        </form>






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