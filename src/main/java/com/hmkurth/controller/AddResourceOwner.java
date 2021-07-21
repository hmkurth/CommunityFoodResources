package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.ResourceOwner;
import com.hmkurth.persistence.GenericDao;
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
 * a servlet to add a food resource to the database
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "AddResourceOwner", urlPatterns = { "/addResourceOwner" } )


public class AddResourceOwner extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<FoodResource> fdao;
    GenericDao<ResourceOwner> odao;
    FoodResource resource;
    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                 the HttpServletRequest object
     *@param  res               the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int resourceId = (int) session.getAttribute("newResourceId");
        resource = (FoodResource) req.getAttribute("newResource"); //get the unsaved resource from the previous request
        logger.debug("This food resource in doget: " + resource.getName());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/addResourceOwner.jsp");
        dispatcher.forward(req, res);

    }
    /**
     * Handles HTTP POST requests.
     *
     * @param req the HttpServletRequest object
     * @param res the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     **/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//todo, clean up duplicate code!
        HttpSession session = req.getSession();

        String url = "/admin/addLocation.jsp";
        ResourceOwner thisOwner;
        resource = (FoodResource) session.getAttribute("newResource"); //get the unsaved resource from the previous request
        logger.info("forwarded food resource = " + resource);
        fdao = new GenericDao<FoodResource>(FoodResource.class);
        odao = new GenericDao<ResourceOwner>(ResourceOwner.class);
        String ownerId = req.getParameter("owner");
        req.setAttribute("selectedOwnerId", ownerId);//for selection in the dropdown menu

        if (ownerId != null) {
            int ownerInt = Integer.parseInt(ownerId);
            logger.debug("ownerInt = : " + ownerInt);

            //set the owner
            if (ownerInt == 9999) {
                //new owner to add, jsp should display additional fields after the first submit is processed, so redirect
                url = "/admin/addResourceOwner.jsp";

            } else {
                if (ownerInt == 8888) {
                    //resource is private, default set in database, id = 8888
                    thisOwner = odao.getById(8888);
                    logger.debug("thisOwner before setting to resource: " + thisOwner);
                    resource.setOwner(null);
                    String message = "you have chosen not to add an owner to the resource " + resource.getName();
                    session.setAttribute("message", message);

                } else {
                    //choose an existing owner from the list
                    thisOwner = odao.getById(ownerInt);
                    resource.setOwner(thisOwner);
                    String message = "you have successfully added the owner, " + resource.getOwner().getName() + " to the resource " + resource.getName();
                    session.setAttribute("message", message);
                    logger.debug("chose an existing owner: " + resource.getOwner().toString());

                }//end else
                url = "/admin/addLocation.jsp";
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);
        }//end if not null



            //if new owner is selected, now grab details from the second form
            String x = req.getParameter("submit2");
            logger.debug("string x = :" + req.getParameter("submit2"));
        if (x != null && x.equals("Submit")) {
                logger.debug("in add new resource owner block");
                thisOwner = new ResourceOwner();
                thisOwner.setName(req.getParameter("ownerName"));
                thisOwner.setWebsite(req.getParameter("website"));
                odao.insert(thisOwner);
                logger.debug("thisOwner before setting to resource: " + thisOwner);
                resource.setOwner(thisOwner);
            logger.debug("thisOwner after setting to resource: " + resource.getOwner().getName());
                String message = "you have successfully added the owner" + resource.getOwner().getName() + " to the resource " + resource.getName() + ". ";
                session.setAttribute("message", message);
                url = "/admin/addLocation.jsp";

                RequestDispatcher dispatcher = req.getRequestDispatcher(url);
                dispatcher.forward(req, res);
            }

    }//end do post
}