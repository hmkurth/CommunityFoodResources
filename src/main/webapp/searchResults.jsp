<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Results" />
<%@include file="head.jsp"%>
<%@include file="header.jsp"%>

<div class="banner">
    <div class="bread-wrap">
        <div class="container no-height">
            <div class="row">
                <div class="col-sm-12">
                    <ol class="breadcrumb">
                        <li>You are here:</li>
                        <li><a href="index.jsp.jsp">Home</a></li>
                        <li>Search Results</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Begin main page content -->
<div class="content" id="main_content">
    <div class="container">

        <div class="row text-center pad-top pad-bottom line-bottom">
            <div class="col-md-12">
                <h1>Search Our Site</h1>
                <form class="form-inline pad-top-sm">
                    <div class="form-group">
                        <label for="type">Search For:</label>
                        <input type="text" id="query" class="form-control input-lg" value="User search query" />
                    </div>
                    <div class="form-group">
                        <label for="location"> &nbsp; Search In:</label>
                        <select class="form-control input-lg" id="location">
                            <option>All</option>
                            <option>Category 1</option>
                            <option>Category 2</option>
                            <option>Category 3</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary btn-lg">Search Site</button>
                </form>
            </div>
        </div>

        <div class="row pad-bottom">
            <div class="col-md-10 col-md-offset-1">
                <h2>Search Results</h2>
                <h3><a href="#">Search result lorem ipsum dolor sit amet,</a></h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. <mark>Duis ac felis ornare</mark>, tempor diam et, fermentum diam. Integer bibendum purus nec nunc ornare pulvinar id eu sapien. Duis ac feugiat ipsum. Sed nec sem a nulla condimentum <mark>lobortis</mark>.</p>
                <p class="small"><a href="#">http://thisisyourawesomesite.com/your-page</a></p>
                <hr />
                <h3><a href="#">Search result lorem ipsum dolor sit amet,</a></h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. <mark>Duis ac felis ornare</mark>, tempor diam et, fermentum diam. Integer bibendum purus nec nunc ornare pulvinar id eu sapien. Duis ac feugiat ipsum. Sed nec sem a nulla condimentum <mark>lobortis</mark>.</p>
                <p class="small"><a href="#">http://thisisyourawesomesite.com/your-page</a></p>
                <hr />
                <h3><a href="#">Search result lorem ipsum dolor sit amet,</a></h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. <mark>Duis ac felis ornare</mark>, tempor diam et, fermentum diam. Integer bibendum purus nec nunc ornare pulvinar id eu sapien. Duis ac feugiat ipsum. Sed nec sem a nulla condimentum <mark>lobortis</mark>.</p>
                <p class="small"><a href="#">http://thisisyourawesomesite.com/your-page</a></p>
                <hr />
                <h3><a href="#">Search result lorem ipsum dolor sit amet,</a></h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. <mark>Duis ac felis ornare</mark>, tempor diam et, fermentum diam. Integer bibendum purus nec nunc ornare pulvinar id eu sapien. Duis ac feugiat ipsum. Sed nec sem a nulla condimentum <mark>lobortis</mark>.</p>
                <p class="small"><a href="#">http://thisisyourawesomesite.com/your-page</a></p>
                <hr />
                <h3><a href="#">Search result lorem ipsum dolor sit amet,</a></h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. <mark>Duis ac felis ornare</mark>, tempor diam et, fermentum diam. Integer bibendum purus nec nunc ornare pulvinar id eu sapien. Duis ac feugiat ipsum. Sed nec sem a nulla condimentum <mark>lobortis</mark>.</p>
                <p class="small"><a href="#">http://thisisyourawesomesite.com/your-page</a></p>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>


        </div>

    </div>
</div>

