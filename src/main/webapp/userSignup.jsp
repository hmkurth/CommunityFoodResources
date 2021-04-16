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


    <form action="index.html">
        <div class="form-group">
            <label for="email_address">Email address</label>
            <input type="email" class="form-control" id="email_address" placeholder="Email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="file_example">File input</label>
            <input type="file" id="file_example">
            <p class="help-block">Example block-level help text here.</p>
        </div>
        <hr />
        <button type="submit" class="btn btn-primary btn-lg">Next Step</button>
    </form>




</div>
<%@include file="footer.jsp"%>