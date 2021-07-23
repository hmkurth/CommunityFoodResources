package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

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
    private final Logger logger = LogManager.getLogger(this.getClass());
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
        //trying to convert to json array for map, can't find these classes,
        // or at least pare down the exact info with no weird commas, would that be better in JS map, reduce filter, or here?? what's faster??
        JSONArray jList = new JSONArray();
        for (FoodResource resource : allMapped) {
            //how to pare down the values, I don't need all of them going to the map

            String latStr = String.valueOf(resource.getLocation().getLat());
            String lngStr = String.valueOf(resource.getLocation().getLat());
            String name = resource.getName();
            String type = resource.getTypeId().getName();
            String desc = resource.getDescription();
            //this is probably cumbersome and dumb, but it's one way i've seen on stack overflow
            //there must be a better way by now than this...
            String resourcePared ="{" + type + "," +  name +"," + desc +  "," + latStr +  "," + lngStr + "}";
            //add the resource to the json string array
            jList.put(resourcePared);

        }
      //  logger.debug("the jList of strings :" + jList);
        session.setAttribute("allMapped", allMapped);
       // req.setAttribute("allMapped", allMapped);
        logger.debug("session attribute allMapped." + session.getAttribute("allMapped"));

        String url = "/mapAll.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);

    }

}