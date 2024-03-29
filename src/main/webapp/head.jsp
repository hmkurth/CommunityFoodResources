<%@include file="taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=0.80">

    <!-- Descriptive meta should this go here in the head, or in individual page heads?
   // <title>Accessible - Home</title>
    <meta name="title" content="max 65 character title">-->
    <meta name="description" content="this is the home page of the community food resource locator app">
    <meta name="keywords" content="comma, separated, list">
    <meta name="author" content="heather kurth">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${title}</title>

    <!-- Latest compiled and minified CSS -->
    <!-- CSS assets -->
    <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/font-awesome.css" rel="stylesheet">

    <!-- JS assets -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap-hover-dropdown.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.smooth-scroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.vide.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.bgswitcher.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/map.js"></script>
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/assets/js/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- Theme specific assets -->
    <link href="${pageContext.request.contextPath}/assets/css/app.css" rel="stylesheet"><!-- Global portal stying -->
    <link href="${pageContext.request.contextPath}/assets/css/banners.css" rel="stylesheet"><!-- Page banners -->
    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

</head>
