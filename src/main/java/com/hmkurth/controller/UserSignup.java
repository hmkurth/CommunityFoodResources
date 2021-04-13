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

/**
 * adapted from FBTR, pawaite
 */

@WebServlet(name = "UserLogin", urlPatterns = { "/userLogin" } )


public class UserSignup extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUserName(req.getParameter("userName"));
        user.setEmail(req.getParameter("emailAddress"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPassword(req.getParameter("password"));
        logger.debug("Adding User: " + user);
        UserRoles role = new UserRoles();
        role.setUser(user);
        role.setRoleName("user");
        user.addRole(role);

            // TODO check if user is already in the database
            GenericDao dao = new GenericDao(User.class);
            dao.insert(user);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/signUpConfirmation" +
                ".jsp");
        dispatcher.forward(req, resp);
    }
}
