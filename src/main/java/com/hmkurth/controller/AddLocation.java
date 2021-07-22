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
    Boolean isNew;

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
        ldao = new GenericDao<>(Location.class);
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
                    isNew = true;
                    session.setAttribute("location", location);
                    session.setAttribute("isNew", isNew);
                    break;
                case "editLocation":
                    //edit existing location
                    location = resource.getLocation();
                    isNew = false;
                    session.setAttribute("isNew", isNew);
                    session.setAttribute("location", location);
                    break;
                default:
                    //connect  an existing location by id to this resource
                    int locationId = Integer.parseInt(req.getParameter("nextOptions"));
                    location = ldao.getById(locationId);
                    resource.setLocation(location);
                    url = "/admin/addContact.jsp";
                    break;
            }

            //save the location to the resource
            fdao.saveOrUpdate(resource);
        }
        //doPost(req, res);
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
        String message;
        String url = "/admin/addLocation.jsp";

        Location location2 = new Location();//holder for the converted location
        resource = (FoodResource) session.getAttribute("newResource"); //get the unsaved resource from the previous request
        location = (Location)session.getAttribute("location");
        String nameDesc=req.getParameter("nameDesc");
        String streetAddress =req.getParameter("streetAddressOrIntersection");
        String city=req.getParameter("city");
        String state=req.getParameter("state");
        String zip=req.getParameter("zip");
        String busInfo=req.getParameter("busInfo");
        String comments=req.getParameter("comments");
        logger.debug("Is this new, isNew variable? " + isNew);
        logger.debug("Is this new, isNew session? " + session.getAttribute("isNew"));

        logger.debug("Location1: " + location);
        logger.debug("Location1from session att: " + session.getAttribute("location"));
        logger.debug("Location1 resource.getLoc: " + resource.getLocation());
        logger.debug("Location1:req att:  " + req.getAttribute("location"));


        String x2 = req.getParameter("submit2");
        logger.debug("value x2 : " + x2);
        if (x2 != null ) {
            logger.debug("in submit2 block");
            logger.debug("2 Location1: " + location);
            logger.debug("value x2 : " + x2);

            logger.debug("2 Location1 resource.getLoc: " + resource.getLocation());
            logger.debug("2 Location1:req att:  " + req.getAttribute("location"));
            logger.debug("2 Location1from session att: " + session.getAttribute("location"));
            location.setNameDesc(nameDesc);
            location.setStreetAddressOrIntersection(streetAddress);
            location.setCity(city);
            location.setState(state);
            location.setZip(zip);
            location.setBusInfo(busInfo);
            location.setComments(comments);

            //Use the api to get the lat and long
            try {
                LocationApiDao locationApiDao = new LocationApiDao();
                location2 = locationApiDao.convertAddressToLatAndLong(location);
                //logger.debug("getting long and lat, location2" + location2.toString());
            } catch (Exception e) {
                logger.error(e);
            }

            //save or insert depending on if new
            if(isNew){
                //if new insert
                //add the location to the database
                dao.insert(location2);
                message = "you have successfully  added a  location to the resource " + resource.getName();
                //then forward to contacts
                url = "/admin/addContact.jsp";
            } else {
               // update
                ldao.saveOrUpdate(location2);
                message = "you have successfully edited this resource";
                //then forward to edit
                url = "/admin/editResources.jsp";
            }
            resource.setLocation(location2);
            // location2.addResource(resource);  resource is getting added twice, so ...
            //save the info on resource
            fdao.saveOrUpdate(resource);
            //update the  req or ses variables
            session.setAttribute("location", location2);
            session.setAttribute("message", message);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}