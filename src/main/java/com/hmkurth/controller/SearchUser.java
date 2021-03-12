package com.hmkurth.controller;

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
 * A simple servlet to welcome the user.
 * @author pwaite, updated 3/4 hmkurth
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
    GenericDao userDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao userDao = new GenericDao(User.class);
       /* if (req.getParameter("submit").equals("search")) {
            req.setAttribute("users", userDao.getByPropertyLike("lastName", req.getParameter("searchTerm")));
        } else {
            req.setAttribute("users", userDao.getAll());
        }*/
        //req.setAttribute("users", userDao.getAll());
        //setting attribute for last name search
        String  searchTerm = req.getParameter("searchTerm");
        String  searchProperty = req.getParameter("searchProperty");
        req.setAttribute("searchResult", userDao.getPropertyByName(searchProperty,searchTerm));
        logger.debug("In search user servlet, " + searchTerm + " " + searchTerm);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}