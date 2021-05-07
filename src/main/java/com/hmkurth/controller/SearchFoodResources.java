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
 * This servlet forwards to the searchFoodResources jsp
 * and processes the POST form data, forwarding to a search results jsp
 */

@WebServlet(
        urlPatterns = {"/searchFoodResources"}
)
//TODO error handling!  check and redirect!!!
public class SearchFoodResources extends HttpServlet {
    GenericDao resourceDao;
    private final Logger logger = LogManager.getLogger(this.getClass());



@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
         resourceDao = new GenericDao(FoodResource.class);
        String  searchTerm = req.getParameter("term");
        String  searchProperty = req.getParameter("categories");
        session.setAttribute("resources", resourceDao.getPropertyByName(searchProperty,searchTerm));
        logger.debug("In search resources servlet, " + searchProperty + " " + searchTerm);

        if (req.getParameter("submit").equals("search")) {
            session.setAttribute("resources", resourceDao.getPropertyByName(searchTerm, searchProperty));
        } else {
            session.setAttribute("resourcesAll", resourceDao.getAll());
        }
        session.setAttribute("resourcesAll", resourceDao.getAll());
        req.setAttribute("resourcesAll", resourceDao.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchFoodResources.jsp");
        dispatcher.forward(req, resp);
    }
}