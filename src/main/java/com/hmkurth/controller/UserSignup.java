package com.hmkurth.controller;


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
import java.util.List;

/**
 * This servlet will take in the user sign up information and add it to the database
 * adapted from FBTR, pawaite
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "UserSignup", urlPatterns = { "/userSignup" } )


public class UserSignup extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    /**
     *  Handles HTTP POST requests.
     *
     *@param  req                 the HttpServletRequest object
     *@param  resp                the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     **/
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        String thisFirstName = req.getParameter("first_name");
        String thisLastName = req.getParameter("last_name");
        String thisUserName = req.getParameter("user_name");
        //check for empty fields
        if (req.getParameter("first_name").isEmpty() || (req.getParameter("last_name").isEmpty() || (req.getParameter("user_name").isEmpty() ||
                (req.getParameter("password").isEmpty() || (req.getParameter("email").isEmpty() )))))
        {
            req.setAttribute("errorMessage", "Please try again, all fields are required to sign up");
            logger.info("User Error");
            RequestDispatcher dispatcher= req.getRequestDispatcher("userSignup.jsp");
            dispatcher.include(req, resp);
        }
        else {
            //add the user to the database
            GenericDao<User> dao = new GenericDao<>(User.class);
            //check if user name exists
            List userExists =  dao.getPropertyByName("userName", thisUserName);
            if (userExists.isEmpty()) {
                user.setUserName(thisUserName);
                user.setFirstName(thisFirstName);
                user.setLastName(thisLastName);
                user.setPassword(req.getParameter("password"));
                //TODO validate email address
                user.setEmail(req.getParameter("email"));
                UserRoles role = new UserRoles();
                role.setUser(user);
                role.setUserName(user.getUserName());
                //all users get assigned role user first
                role.setRoleName("user");
                user.addRole(role);
                dao.insert(user);
                RequestDispatcher dispatcher = req.getRequestDispatcher("signUpSuccess.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("errorMessage", "That user name is already in use, please try another variation");
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/userSignup.jsp");
            dispatcher.forward(req, resp);
        }

    }



}
