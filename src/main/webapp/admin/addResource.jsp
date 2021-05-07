
<%@include file="/taglib.jsp"%>
<jsp:include page="../head.jsp"/>
<title>Add Resource</title>

<jsp:include page="../header.jsp"/>

<div class="login-container">
    <h1 class="text-center pad-bottom-sm">Add A New Resource</h1>


    <!--TODO form verification! auto complete attribute -->

    <form action="${pageContext.request.contextPath}/addResource"  method="post" autocomplete="on">
        <div class="form-group">
            <label for="first_name">First Name</label>
            <input type="text" class="form-control" id="first_name" name ="first_name" placeholder="First Name" autocomplete="given-name" aria-required="true" required>
        </div>
        <div class="form-group">
            <label for="last_name">Last Name</label>
            <input type="text" class="form-control" id="last_name" name ="last_name"placeholder="Last Name"autocomplete="family-name" aria-required="true"  required>
        </div>
        <!--TODO make sure username is not in use  -->
        <div class="form-group">
            <label for="user_name">User Name</label>
            <input type="text" class="form-control" id="user_name" name ="user_name"placeholder="User Name"autocomplete="username" aria-required="true"required>
        </div>
        <!--TODO form verification requirements for pw verification?   put the dots in instead of letters? no-->
        <div class="form-group">
            <label for="user_password">Password</label>
            <input type="text" class="form-control" id="user_password" name ="password"placeholder="Password(password specs here)" autocomplete="new-password" aria-required="true" required>
        </div>
        <!--TODO form verification ensure it's a valid email address-->
        <div class="form-group">
            <label for="email_address">Email address</label>
            <input type="email" class="form-control" id="email_address" name ="email" placeholder="Email"  autocomplete="email" aria-required="true" required>
        </div>

        <hr />
        <button type="submit" class="btn btn-primary btn-lg">Submit</button>
    </form>




</div>
<jsp:include page="../footer.jsp"/>