<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<%@include file="header.jsp"%>
//TODO
<body class="login-bg">

<div class="login-container">
    <h1 class="text-center pad-bottom-sm">Register</h1>

    <div class="progress">
        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100" style="width: 25%">
            Step 1 of 7
        </div>
    </div>
<!--TODO form verification!  -->

    <form action="/userSignup"  method="post">
        <div class="form-group">
            <label for="first_name">First Name</label>
            <input type="text" class="form-control" id="first_name" placeholder="First Name" required>
        </div>
        <div class="form-group">
            <label for="last_name">Last Name</label>
            <input type="text" class="form-control" id="last_name" placeholder="Last Name" required>
        </div>
        <!--TODO make sure username is not in use  -->
        <div class="form-group">
            <label for="user_name">User Name</label>
            <input type="text" class="form-control" id="user_name" placeholder="User Name" required>
        </div>
        <!--TODO form verification requirements for pw verification?   put the dots in instead of letters? no-->
        <div class="form-group">
            <label for="user_password">Password</label>
            <input type="text" class="form-control" id="user_password" placeholder="Password(password specs here" required>
        </div>
        <!--TODO form verification ensure it's a valid email address-->
        <div class="form-group">
            <label for="email_address">Email address</label>
            <input type="email" class="form-control" id="email_address" placeholder="Email" required>
        </div>

        <hr />
        <button type="submit" class="btn btn-primary btn-lg">Next Step</button>
    </form>




</div>
<%@include file="footer.jsp"%>