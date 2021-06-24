package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.Location;
import com.hmkurth.persistence.GenericDao;
import com.hmkurth.persistence.LocationApiDao;
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
 * This servlet will allow a user to enter a location into the db,
 * It will also use the geolocation Api to get the lat and lng of that location, and input that as well
 * TODO how to use maplocation, do i really need that?? I think everything i need  is in the api location class,
 * should that be in a different servlet, or do i want to do it all while inserting/adding?
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "AddLocation", urlPatterns = { "/addLocation" } )


public class AddLocation extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Handles HTTP POST requests.
     *
     * @param req  the HttpServletRequest object
     * @param resp the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao dao = new GenericDao(Location.class);
        GenericDao fdao = new GenericDao(FoodResource.class);
        HttpSession session = req.getSession();
        String url = "admin/addLocation.jsp";

        FoodResource resource = (FoodResource) session.getAttribute("newResource"); //get the unsaved resource from the previous request
logger.debug("food resource at add location do post");
        //do they want to add a location to this resource? if not continue to contacts
        String x = req.getParameter("submit");

        logger.debug("options value : " + req.getParameter("nextOptions"));
        if (x != null && x.equals("Next")) {
            if (req.getParameter("nextOptions").equals("noLocation")) {
                //no location, set null? do i need to actually set it??
                //resource.setLocation(null);
                logger.debug("resource location set to null");
                //then forward to addContacts
                url = "/admin/addContact.jsp";

            } else { //forward, and next form will be shown
                RequestDispatcher dispatcher = req.getRequestDispatcher(url);
                dispatcher.forward(req, resp);
            }

            String x2 = req.getParameter("submit2");
            if (x2 != null && x.equals("Add Location")) {
                //add a new location
                Location location = new Location();
                location.setNameDesc(req.getParameter("nameDesc"));
                location.setStreetAddressOrIntersection(req.getParameter("streetAddressOrIntersection"));
                location.setCity(req.getParameter("city"));
                location.setState(req.getParameter("state"));
                location.setZip(req.getParameter("zip"));
                location.setBusInfo(req.getParameter("busInfo"));
                location.setComments(req.getParameter("comments"));
                logger.debug("Adding Location: " + location);
                logger.debug("Adding resource to location, resource= : " + resource.toString());

                //Use the api to get the lat and long TODO
                try {
                    LocationApiDao locationApiDao = new LocationApiDao();
                    location = locationApiDao.convertAddressToLatAndLong(location);
                    logger.debug("getting long and lat" + location.toString());
                    } catch (Exception e) {
                        logger.error(e);
                    }
                    resource.setLocation(location);
                    assert location != null;
                    location.setResourceId(resource);
                    //add the location to the database
                    dao.insert(location);
                    //then forward to contacts
                    url = "/admin/addContact.jsp";
                }

        }//end try
//TOdo confirmation,
        //logger.debug("Adding resource to location, resource= : " + resource.toString());
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }

}

