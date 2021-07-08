package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * a servlet to confirm a food resource to the database
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "ConfirmResource", urlPatterns = { "/confirmResource" } )


public class ConfirmResource extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<FoodResource> fdao = new GenericDao<>(FoodResource.class);
    FoodResource resource;

    /**
     * Handles HTTP POST requests.
     *
     * @param req the HttpServletRequest object
     * @param res the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     **/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String url = "/admin/confirmResource.jsp";
        resource = (FoodResource) session.getAttribute("newResource"); //get the unsaved resource from the previous request
        String x = req.getParameter("submit");

        logger.debug("options value : " + req.getParameter("confirmAdd"));
        if (x != null && x.equals("Next")) {
            switch (req.getParameter("confirmAdd")) {
                case "addData":
                    fdao.insert(resource);
                    String message = "you have successfully submitted a  food resource, " + resource.getName() + ", an admin will double check entry before adding to the live database";
                    //todo show map location and confirm that
                    session.setAttribute("message", message);
                    url = "/admin/adminHome.jsp";
                    break;
                case "addLocation":
                    url = "/addLocation";
                    break;
                case "addContact":
                    url = "/addContact";
                    break;
                case "addResourceOwner":
                    url = "/addResourceOwner";
                    break;
                case "addResource":
                    //todo more edit capabilities,
                    url = "/admin/addResource.jsp";
                    break;
            }
        }




        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, res);


    }
}