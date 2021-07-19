package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.Location;
import com.hmkurth.persistence.GenericDao;
import com.hmkurth.persistence.LocationApiDao;
import lombok.SneakyThrows;
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
 * This servlet will display details for the chosen resource
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "DisplayDetails", urlPatterns = { "/displayDetails" } )


public class DisplayDetails extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    LocationApiDao ldao;

    /**
     * Handles HTTP GET requests.
     *
     * @param req the HttpServletRequest object
     * @param res the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    @SneakyThrows
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        GenericDao<FoodResource> fdao;
        String url = "/displayDetails.jsp";
        fdao = new GenericDao<>(FoodResource.class);
        ldao = new LocationApiDao();


        String idToDisplay = req.getParameter("details");
        if (idToDisplay != null) {
            int intToDisplay = Integer.parseInt(idToDisplay);
            FoodResource toDisplay = fdao.getById(intToDisplay);
            req.setAttribute("toDisplay", toDisplay);
            //try to make a marker for the map
            Location location = toDisplay.getLocation();
            if(location != null) {
                ldao.addMarker(location);
            }

        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);
    }
}
