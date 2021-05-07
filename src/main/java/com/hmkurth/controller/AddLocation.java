package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.Location;
import com.hmkurth.entity.User;
import com.hmkurth.entity.UserRoles;
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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao dao = new GenericDao(Location.class);
        GenericDao fdao = new GenericDao(FoodResource.class);
        Location returnedLocation = null;

        Location location = new Location();
        location.setNameDesc(req.getParameter("nameDesc"));
        location.setStreetAddressOrIntersection(req.getParameter("streetAddressOrIntersection"));
        location.setCity(req.getParameter("city"));
        location.setState(req.getParameter("state"));
        location.setZip(req.getParameter("zip"));
        location.setBusInfo(req.getParameter("busInfo"));
        location.setComments(req.getParameter("comments"));


        logger.debug("Adding Location: " + location);
        //TODO connect to food resource that it references, need an id...
        String resourceId = req.getParameter("resourceId");
        int id =Integer.parseInt(resourceId);
        FoodResource resource = (FoodResource) fdao.getById(id);

        logger.debug("Adding resource to location, resource= : " + resource.toString());

        //Use the api to get the lat and long TODO
        try {
            LocationApiDao locationApiDao = new LocationApiDao();
            location = locationApiDao.convertAddressToLatAndLong(location);
            logger.debug("getting long and lat" +  location.toString());
        } catch (Exception e) {
            logger.error(e);
        }
        resource.setLocation(location);
        assert location != null;
        location.setResourceId(resource);

        //add the location to the database

            dao.insert(location);

            //TOdo confirmation,
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/addLocationSuccess.jsp");
            dispatcher.forward(req, resp);
        }

    }

