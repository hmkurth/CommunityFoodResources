# Application Flow
Is modified, but I'm very insecure about it ;)
I'm not sure how much to implement for the MVP,
I'm not sure at all how much work each thing takes...

### com.hmkurth.entity.User Sign up

1. com.hmkurth.entity.User chooses sign up on the menu (available on all pages, unless the user
   is signed in already).
1. com.hmkurth.entity.User fills out the sign up form and submits.
1. Request goes to sign up servlet.
1. Servlet creates a user object and then creates user in the database.
1. Response to user confirming addition (show a message on the jsp)

### com.hmkurth.entity.User Sign In

1. com.hmkurth.entity.User chooses sign in on the menu (available on all pages, unless the user
   is signed in already).
1. com.hmkurth.entity.User enters username and password on form and submits.
1. If user is authenticated, the server will handle allowing access to edit
   pages.  JDBCRealm used for authentication (users, users_roles, and roles table).
1. If authentication fails, show error message/page.

### View WELCOME, Home page

1. Page sends a request to view resources servlet along with criteria
   (all, zipcode search, date, type, resource owner/provider  etc).
1. Servlet uses the resourcereports dao to select reports according to criteria
1. Dao performs select and creates report objects from results.
1. Dao returns list of report matching criteria to servlet.
1. Servlet sends list back to resource reports jsp.
1. resource reports jsp displays the reports.
1. Consider paging results so page does not get super long and too much data
   is sent.

### View individual Resources

1. Page sends a request to view individual Resources servlet along with criteria
   (all, region, name, etc).
1. Servlet uses the individual Resources dao to select individual Resources according to criteria
        ###########can't this be done by a link to the individual resources? 
         ###if i'm only choosing one result to view at a time????#######
1. Dao performs select and creates individual Resources objects from results.
1. Dao returns  individual Resources matching criteria/button id??? to servlet.
1. Servlet sends list back to individual Resources  jsp.
1. individual Resources reports jsp displays the trails.


### About

1. Static page - html only?
1. Consider making contact info configurable.





###DO I HAVE TIME TO DO THE ADD RESOURCES?  WOULD I WANT TO??????  
##Admin could review, could grant certain people add priveledges
##alternately, could have a mailto button if plain users want to add a resource and send and email to admin




###

### Add Resource Report
1. Option only available to logged in users with proper role
1. com.hmkurth.entity.User selects trail to report on
1. com.hmkurth.entity.User enters trail report details
1. Details are sent to Add Trail Report servlet
1. Servlet creates trail report object
1. Servlet sends object to dao
1. Dao adds report to the database
1. Servlet sends confirmation to report page that report has been added.

### Add Resource
1. Option only available to logged in users with proper role
1. com.hmkurth.entity.User enters trail  details
1. Details are sent to Add Trail  servlet
1. Servlet creates trail  object
1. Servlet sends object to dao
1. Dao adds trail to the database
1. Servlet sends confirmation to trail page that trail has been added.








 