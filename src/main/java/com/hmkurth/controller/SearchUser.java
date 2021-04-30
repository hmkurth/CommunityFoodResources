package com.hmkurth.controller;

import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.User;
import com.hmkurth.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A simple servlet to search food resources.
 * @author pwaite, updated 3/4 hmkurth
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)
//TODO error handling!  check and redirect!!!
public class SearchUser extends HttpServlet {
    GenericDao resourceDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        HttpSession session = req.getSession();
         resourceDao = new GenericDao(FoodResource.class);
        //setting attribute for last name search
        String  searchTerm = req.getParameter("type");
        String  searchProperty = req.getParameter("location");
        session.setAttribute("resources", resourceDao.getPropertyByName(searchProperty,searchTerm));
        logger.debug("In search user servlet, " + searchTerm + " " + searchTerm);

        if (req.getParameter("submit").equals("search")) {
            session.setAttribute("resources", resourceDao.getPropertyByName(searchTerm, searchProperty));
        } else {
            session.setAttribute("resourcesAll", resourceDao.getAll());
        }
        session.setAttribute("resourcesAll", resourceDao.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchResults.jsp");
        dispatcher.forward(req, resp);
    }
}