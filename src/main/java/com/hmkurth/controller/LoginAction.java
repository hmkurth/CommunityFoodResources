package com.hmkurth.controller;

import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.User;
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
 * this servlet will redirect to the homepage after logging in
 * @author hmkurth
 */

@WebServlet(
        urlPatterns = {"/loginAction"}
)

public class LoginAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String url;
    logger.info("The logged in user; " + req.getRemoteUser() + "has a role of  'user' : "  + req.isUserInRole("user") );
    //if the user is an admin, redirect to admin home page, otherwise go to index
     if(req.isUserInRole("admin")) {
          url = "/admin/adminHome.jsp";
         GenericDao dao= new GenericDao(User.class);
         List all = dao.getAll();
         req.setAttribute("AllUsers", all);
        } else {
          url = "/index.jsp";
     }
     //try to set session attribute so you can determine if a user is logged in , can I do it here?
    if(req.getRemoteUser() != null) {
        req.getSession().setAttribute("loggedInUser", req.getRemoteUser());
    }
    RequestDispatcher dispatcher = req.getRequestDispatcher(url);
    dispatcher.forward(req, resp);

    }
}