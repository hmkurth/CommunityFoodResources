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
 * This servlet will allow a user to enter a location to search for nearby resources,
 * It will also use the geolocation Api to get the lat and lng of that location,
 *
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "SearchByLocation", urlPatterns = { "/searchByLocation" } )


public class SearchByLocation extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao dao = new GenericDao(Location.class);
        GenericDao fdao = new GenericDao(FoodResource.class);

        Location location = new Location();
        location.setStreetAddressOrIntersection(req.getParameter("streetAddressOrIntersection"));
        location.setCity(req.getParameter("city"));
        location.setState(req.getParameter("state"));
        location.setZip(req.getParameter("zip"));
        logger.debug("searchBy Location: " + location);

        //Use the api to get the lat and long TODO
        try {
            LocationApiDao locationApiDao = new LocationApiDao();
            location = locationApiDao.convertAddressToLatAndLong(location);
            logger.debug("getting long and lat" +  location.toString());
        } catch (Exception e) {
            logger.error(e);
        }

        //find the nearest resources TODO, param of what type of resource user wants, or all
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/addLocationSuccess.jsp");
        dispatcher.forward(req, resp);
    }

}

