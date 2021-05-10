package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.User;
import com.hmkurth.entity.UserRoles;
import com.hmkurth.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * a servlet to add a food resource to the database
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "AddResource", urlPatterns = { "/addResource" } )


public class AddResource extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     *  Handles HTTP POST requests.
     *
     *@param  req                 the HttpServletRequest object
     *@param  resp                the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**todo change for food resources
        FoodResource resource = new FoodResource();

        resource.setFirstName(req.getParameter("first_name"));
        resource.setLastName(req.getParameter("last_name"));
        resource.setUserName(req.getParameter("user_name"));
        logger.debug("Adding Username: " + resource.getUserName());
        resource.setPassword(req.getParameter("password"));
        resource.setEmail(req.getParameter("email"));
        logger.debug("Adding User: " + user);
        UserRoles role = new UserRoles();
        role.setUser(user);
        role.setUserName(user.getUserName());
        role.setRoleName("user");
        user.addRole(role);



        //set the user in a ?session variable

        if (req.getParameter("first_name").isEmpty() || (req.getParameter("last_name").isEmpty() || (req.getParameter("user_name").isEmpty() ||
                (req.getParameter("password").isEmpty() || (req.getParameter("email").isEmpty() )))))
        {
            req.setAttribute("errorMessage", "Please try again, all fields are required to sign up");
            logger.info("User Error");
            RequestDispatcher dispatcher= req.getRequestDispatcher("userSignup.jsp");
            dispatcher.include(req, resp);
        }
        else
        {
            //add the uses to the database
            // TODO check if user is already in the database
            GenericDao dao = new GenericDao(User.class);

            dao.insert(resource);

            //TOdo set the user in the session?? check if this is duplicating entries
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/addResourceSuccess.jsp");
            dispatcher.forward(req, resp);
        }

    }

         */

}
}