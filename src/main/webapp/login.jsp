<%@include file="taglib.jsp"%>
<c:import url="/head.jsp" />

    <title>Login</title>


<c:import url="/head.jsp" />
<c:import url="/header.jsp" />


<FORM ACTION="j_security_check" METHOD="POST">
    <TABLE>
        <TR><TD>User name: <INPUT TYPE="TEXT" NAME="j_username">
        <TR><TD>Password: <INPUT TYPE="PASSWORD" NAME="j_password">
        <TR><TH><INPUT TYPE="SUBMIT" VALUE="Log In">
    </TABLE>
</FORM>
</body>
<!-- footer -->
<c:import url="/footer.jsp" />
</html>

