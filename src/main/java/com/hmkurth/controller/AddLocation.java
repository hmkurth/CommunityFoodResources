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
import java.util.List;

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
    FoodResource resource;
    Location location;
    GenericDao<FoodResource> fdao;
    GenericDao<Location> ldao;

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
        HttpSession session = req.getSession();
        GenericDao<FoodResource> fdao;
        fdao = new GenericDao<>(FoodResource.class);
        ldao= new GenericDao<>(Location.class);
        List<Location> allLocations = ldao.getAll();
        req.setAttribute("allLocations", allLocations);
        FoodResource resource = (FoodResource) session.getAttribute("newResource");

        session.setAttribute("location", location);
        String url = "/admin/addLocation.jsp";

        String x = req.getParameter("submit");

        logger.debug("options value : " + req.getParameter("nextOptions"));
        if (x != null && x.equals("Next")) {
            switch (req.getParameter("nextOptions")) {
                case "noLocation":
                    //no location, set null? do i need to actually set it??
                    resource.setLocation(null);
                    //?ipdate the resource now??
                    String message = "you have chosen not to add an location to the resource ";
                    session.setAttribute("message", message);
                    logger.debug("resource location set to null");
                    //then forward to addContacts
                    url = "/admin/addContact.jsp";
                    break;
                case "addNewLocation":
                    //new location
                    location = new Location();
                    break;
                case "editLocation":
                    //edit existing location
                    location = resource.getLocation();

                    break;
                default:
                    //connect  an existing location by id to this resource
                    int locationId = Integer.parseInt(req.getParameter("nextOptions"));
                    location = ldao.getById(locationId);
                    resource.setLocation(location);
                    url = "/admin/addContact.jsp";
                    break;
            }
            //save location to an attribute, set?
            req.setAttribute("location", location);
            //save the location to the resource
            fdao.saveOrUpdate(resource);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);
    }


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
      location = (Location) req.getAttribute("location");
        resource = (FoodResource) session.getAttribute("newResource"); //get the unsaved resource from the previous request
        String url  = null;
        location.setNameDesc(req.getParameter("nameDesc"));
        location.setStreetAddressOrIntersection(req.getParameter("streetAddressOrIntersection"));
        location.setCity(req.getParameter("city"));
        location.setState(req.getParameter("state"));
        location.setZip(req.getParameter("zip"));
        location.setBusInfo(req.getParameter("busInfo"));
        location.setComments(req.getParameter("comments"));

//if new location is selected, grab details from second form
        String x2 = req.getParameter("submit2");
        if (x2.equals("Add Location")) {
            logger.debug("in submit2 block");
            logger.debug("value x2 : " + x2);
            //add a new location
            Location location2 = new Location();//location after mapping

          //  logger.debug("Adding Location1: " + location);
          //  logger.debug("Adding resource to location, resource= : " + resource.toString());

            //Use the api to get the lat and long TODO
            try {
                LocationApiDao locationApiDao = new LocationApiDao();
                location2 = locationApiDao.convertAddressToLatAndLong(location);
                //logger.debug("getting long and lat, location2" + location2.toString());
            } catch (Exception e) {
                logger.error(e);
            }

            assert location2 != null;
            ldao.saveOrUpdate(location2);
            resource.setLocation(location2);
           // location2.addResource(resource);  resource is getting added twice, so ...
            //add the location to the database
            dao.insert(location2);
            //save the info on resource
            fdao.saveOrUpdate(resource);
            String message = "you have successfully  added a  location to the resource " + resource.getName();
            //update the  req or ses variables
            session.setAttribute("location", location);
            session.setAttribute("message", message);

            //then forward to contacts
            url = "/admin/addContact.jsp";
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
       }//end if submit2


    }
}


