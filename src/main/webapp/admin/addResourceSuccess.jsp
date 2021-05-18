<%@ page import="java.util.Enumeration" %>
<%@include file="../taglib.jsp"%>
    <jsp:include page="../head.jsp"/>
    <title>Add Resource Success</title>
    <!--TODO show user the added location and resource, maybe confirm? -->
    <jsp:include page="../header.jsp"/>

    <h1>You have successfully added the resource</h1>
   <!--  https://www.tutorialspoint.com/jsp/jsp_form_processing.htm -->
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