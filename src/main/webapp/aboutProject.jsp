<%--
  Created by IntelliJ IDEA.
  User: heath
  Date: 4/18/2021
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="taglib.jsp"%>
<jsp:include page="head.jsp" />
<jsp:include page="header.jsp" />
    <title>About This Project</title>
<!-- import files should have "are relative references to a file in the same directory as the JSP." -->


<!-- Begin main page content -->
<div class="content" id="main_content">
    <div class="container">

        <div class="row">
            <div class="col-md-10 col-md-offset-1">

                <div class="row pad-bottom">
                    <div class="col-sm-12">

                        <h2>Thanks for stopping by! </h2>

                        <p class="lead"> I'm Heather and this site is a Java web service that I have been working on through my degree in Web Development at MATC.
                        <hr />

                        <h3>Pursuing Passion...</h3>
                        <p>In my former career as a chef at Epic I had the opportunity to create relationships and programs to get our excess food into the hands of the community.
                            This led to the creation of an awesome collaboration between Epic and  Badger Prairie Needs Network that is still going strong today, and I will always be proud of.
                            We started Community Meals on Saturdays, and eventually came together to help create the Kitchen to Table food recovery program.</p>

                        <p> I knew that I had to find something I was as passionate about in my new field, software and web development.
                            I found that opportunity during the summer of 2020, in the middle of an unprecedented global epidemic.</p>

                     <p>   I was a member of a few different community aid groups on Facebook, and a common theme was searching and sharing food resources.
                        Many people had stepped up and created unique programs and events to share and get food to people who needed it, it was amazing to witness this community outpouring to address the need of food insecurity. </p>

                      <p>  I wanted to somehow pitch in with my new found skills, and I noticed a need for some kind of service that collated all of these wonderful resources into a searchable application.
                          This would include links to government programs, community programs, public and private services, community aid and support groups.</p>

                        <br/>
                        <h3>The Development Process</h3>
                        <p>And so my 'Community Food Locator' idea was birthed. I found the opportunity to develop this idea in 2 of my classes.
                            I did a lot of the idea development and research in Agile Development. This is the part that I enjoy; the user research, the design process, all of the ideas just flowing around.</p>
                        <br/>
                        <p> <strong>What I got from the community during the development stage was valuable information, they wanted practical information about the resources.</strong>
                        </p>
                        <ul>
                            <li>Who am I targeting with this app and how can I best address the need of that audience? </li>
                            <li>What needs to be considered in the design to address those issues? </li>
                            <li>what type of documentation is needed? </li>
                            <li>Do they offer delivery, if so, to what area? </li>
                            <li>Are there bus stops nearby? (This information still needs to be populated)</li>
                            <li> What days of the week was this resource available?</li>
                        </ul>

                        <p>
                            This feedback led to an expanding of the database fields and tables to include this valuable information, and work in implementing a geolocation API.
                            Next came actually implementing all those pie in the sky ideas, however, easier planned than implemented, and I was not as confident in my ability to do that.
                            My Enterprise Java class with Paula Waite afforded me the opportunity to bring this project to fruition. I wanted to focus on accessibility and providing information that my user research said people using the service would want.
                            I wanted to provide the an easy to read, simple web interface for them, a way to link to alternate languages, search options to find services that fit the needs identified earlier.</p>

                        <p>
                            I know I have a lot more work to do! I would love to find anyone with more experience that would like mentor or quality check this work in some capacity.
                            I am also trying to break into the development community, so if you know of any entry level opportunities, I would love to hear them.</p>
                        <h2><a href="mailto:hmkurth@madisoncollege.edu"> Contact me if you would like to offer support or feedback! </a></h2>

                        <a href="https://github.com/hmkurth/CommunityFoodResources">  <h3>Checkout the code on GitHub</h3></a>



                    </div>
                </div>

            </div>
        </div>

    </div>


<jsp:include page="footer.jsp" />