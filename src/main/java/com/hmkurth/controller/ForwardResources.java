package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This is the servlet for food pantry search, the only purpose is to load the resources from the dao into an attribute and
 * forward to the jsp
 *
 * @author hmkurth
 */
@WebServlet(
        name = "ForwardResources",
        urlPatterns = { "/forwardResources" }
)
public class ForwardResources extends HttpServlet {



    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                 the HttpServletRequest object
     *@param  res                the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        GenericDao dao = new GenericDao(FoodResource.class);
        List<FoodResource> allPantries = null;
        List<FoodResource> allFreeLittlePantries = null;
        List<FoodResource> allMeals = null;
        List<FoodResource> govResources = null;
        List<FoodResource> allComAid = null;
        List<FoodResource> allOther= null;

        //all resources THAT ARE VERIFIED TODO figure out the best way to use multipe queries or filter through the list!!
        List<FoodResource> allVerifiedResources = dao.getByPropertyEqualToBoolean("verificationStatus", true);
        req.setAttribute("resourcesAll", allVerifiedResources);
//trying to sort by resources
        for(FoodResource resource :  allVerifiedResources){
            int thisType = resource.getTypeId().getId();

            if(thisType == 1){
                //that is type pantry, add to the pantry list
                allPantries.add(resource);
                req.setAttribute("allPantries", allPantries);
            } else if (thisType == 2){
                //free little pantries
                allFreeLittlePantries.add(resource);
                req.setAttribute("allFreeLittlePantries", allFreeLittlePantries);

            }
        }


        String url = "/foodPantries.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);

    }

}
