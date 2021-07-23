package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the servlet for food pantry search, the only purpose is to load the resources from the dao into an attribute and
 * forward to the jsp
 *
 * @author hmkurth
 */
@WebServlet(
        name = "MapAll",
        urlPatterns = { "/mapAll" }
)
public class MapAll extends HttpServlet {

    /**
     * Handles HTTP GET requests.
     *
     * @param req the HttpServletRequest object
     * @param res the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        GenericDao dao = new GenericDao(FoodResource.class);
        HttpSession session = req.getSession();
        List<FoodResource> allMapped = new ArrayList<FoodResource>();
        List<FoodResource> allVerifiedResources = dao.getByPropertyEqualToBoolean("verificationStatus", true);
        req.setAttribute("resourcesAll", allVerifiedResources);


        //filter out resources without valid locations
        for (FoodResource resource : allVerifiedResources) {
            String latStr= String.valueOf(resource.getLocation().getLat());
            String lngStr= String.valueOf(resource.getLocation().getLat());

            if ((resource.getLocation().getLat() != null) && ((resource.getLocation().getLng() != null))) {

                allMapped.add(resource);
            }
        }
        session.setAttribute("allMapped", allMapped);
        req.setAttribute("allMapped", allMapped);

        String url = "/mapAll.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);

    }

}