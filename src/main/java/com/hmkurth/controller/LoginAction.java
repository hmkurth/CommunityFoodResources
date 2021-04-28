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
logger.info("The logged in user; " + req.getRemoteUser() + "has a role of  'user' : "  + req.isUserInRole("user") );



        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}