package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  This is the servlet for employee search, the only purpose is to load the resources from the dao into an attribute and
 *  forward to the jsp
 *@author    hmkurth
 */
@WebServlet(
        name = "ForwardPantries",
        urlPatterns = { "/forwardPantries" }
)
public class ForwardPantries extends HttpServlet {



    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                 the HttpServletRequest object
     *@param  res                the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        GenericDao dao = new GenericDao(FoodResource.class);
        req.setAttribute("resourcesAll", dao.getAll());

        String url = "/foodPantries.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);

    }

}
