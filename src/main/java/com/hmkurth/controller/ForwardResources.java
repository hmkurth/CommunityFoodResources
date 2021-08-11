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
        HttpSession session = req.getSession();
        GenericDao dao = new GenericDao(FoodResource.class);
        List<FoodResource> allPantries = new ArrayList<FoodResource>() ;
        List<FoodResource> allFreeLittlePantries = new ArrayList<FoodResource>();
        List<FoodResource> allMeals = new ArrayList<FoodResource>();
        List<FoodResource> govResources = new ArrayList<FoodResource>();
        List<FoodResource> allComAid = new ArrayList<FoodResource>();
        List<FoodResource> allOther= new ArrayList<FoodResource>();

        //all resources THAT ARE VERIFIED TODO figure out the best way to use multipe queries or filter through the list!!
        List<FoodResource> allVerifiedResources = dao.getByPropertyEqualToBoolean("verificationStatus", true);
        session.setAttribute("resourcesAll", allVerifiedResources);
        //trying to sort by resources, this is pretty clunky, could probably be streamlined
        for(FoodResource resource :  allVerifiedResources){
            int thisType = resource.getTypeId().getId();

            if(thisType == 1){
                //that is type pantry, add to the pantry list
                allPantries.add(resource);
            } else if (thisType == 2 || thisType == 3){
                //free little pantries and fridges
                allFreeLittlePantries.add(resource);
            } else if (thisType == 4){
                allMeals.add(resource);
            } else if (thisType == 5) {
                govResources.add(resource);
            }  else if (thisType ==6){
                allComAid.add(resource);
          } else if (thisType == 7) {
        //other
              allOther.add(resource);
            }
        }
        session.setAttribute("allPantries", allPantries);
        session.setAttribute("allFreeLittlePantries", allFreeLittlePantries);
        session.setAttribute("allMeals", allMeals);
        session.setAttribute("govResources", govResources);
        session.setAttribute("allComAid", allComAid);
        session.setAttribute("allOther", allOther);


        String url = "/resourceLanding.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);

    }

}
