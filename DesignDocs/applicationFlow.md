# Application Flow
NOT MODIFIED YET!!

### User Sign up

1. User chooses sign up on the menu (available on all pages, unless the user
   is signed in already).
1. User fills out the sign up form and submits.
1. Request goes to sign up servlet.
1. Servlet creates a user object and then creates user in the database.
1. Response to user confirming addition (show a message on the jsp)

### User Sign In

1. User chooses sign in on the menu (available on all pages, unless the user
   is signed in already).
1. User enters username and password on form and submits.
1. If user is authenticated, the server will handle allowing access to edit
   pages.  JDBCRealm used for authentication (users, users_roles, and roles table).
1. If authentication fails, show error message/page.

### View Trail Report

1. Page sends a request to view trail reports servlet along with criteria
   (all, region, date, name, etc).
1. Servlet uses the trailreports dao to select reports according to criteria
1. Dao performs select and creates report objects from results.
1. Dao returns list of report matching criteria to servlet.
1. Servlet sends list back to trail reports jsp.
1. Trail reports jsp displays the reports.
1. Consider paging results so page does not get super long and too much data
   is sent.

### View Trail

1. Page sends a request to view trail servlet along with criteria
   (all, region, name, etc).
1. Servlet uses the trail dao to select trails according to criteria
1. Dao performs select and creates trail objects from results.
1. Dao returns list of trails matching criteria to servlet.
1. Servlet sends list back to trail  jsp.
1. Trail reports jsp displays the trails.
1. Consider paging results so page does not get super long and too much data
   is sent.

### About

1. Static page - html only?
1. Consider making contact info configurable.

### Add Trail Report
1. Option only available to logged in users with proper role
1. User selects trail to report on
1. User enters trail report details
1. Details are sent to Add Trail Report servlet
1. Servlet creates trail report object
1. Servlet sends object to dao
1. Dao adds report to the database
1. Servlet sends confirmation to report page that report has been added.

### Add Trail
1. Option only available to logged in users with proper role
1. User enters trail  details
1. Details are sent to Add Trail  servlet
1. Servlet creates trail  object
1. Servlet sends object to dao
1. Dao adds trail to the database
1. Servlet sends confirmation to trail page that trail has been added.








 