package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.Location;
import com.hmkurth.persistence.GenericDao;
import com.hmkurth.persistence.LocationApiDao;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This servlet will allow a user to enter a location to search for nearby resources,
 * It will also use the geolocation Api to get the lat and lng of that location,
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "SearchByLocation", urlPatterns = { "/searchByLocation" } )
@Log4j2

public class SearchByLocation extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @SneakyThrows
    @Override
    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                 the HttpServletRequest object
     *@param  res                the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     **/
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
       LocationApiDao lApiDao = new LocationApiDao();
        GenericDao fdao = new GenericDao(FoodResource.class);
        Location location = new Location();
        location.setStreetAddressOrIntersection(req.getParameter("streetAddressOrIntersection"));
        location.setCity(req.getParameter("city"));
        location.setState(req.getParameter("state"));
        location.setZip(req.getParameter("zip"));
        logger.debug("searchBy Location: " + location);
        log.debug("searchBy Location Log4j annotation: " + location);

        //TODO check if it is a valid location
        //Use the api to get the lat and long
        try {
            LocationApiDao locationApiDao = new LocationApiDao();
            location = locationApiDao.convertAddressToLatAndLong(location);
            logger.debug("getting long and lat" +  location.toString());
        } catch (Exception e) {
            logger.error(e);
        }

        //find the nearest resources TODO, param of what type of resource user wants, or all
        //need to find a way use both lat and long coords
        //take the lat and lng out of the newly transformed address, pass to ldao with distance
        int page= 1;
        List<Integer> results = lApiDao.getNearByLocations(location.getLat(), location.getLng(),page);
        req.setAttribute("nearLocations", results);
        logger.debug(results.toString());
        log.debug(results.toString());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/locationSearchResults.jsp");
        dispatcher.forward(req, resp);
    }

}

