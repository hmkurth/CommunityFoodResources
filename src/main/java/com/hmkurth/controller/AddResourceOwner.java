package com.hmkurth.controller;


import com.hmkurth.entity.Contact;
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
    GenericDao<Contact> cdao;
    FoodResource resource;


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
        HttpSession session  = req.getSession();
        fdao = new GenericDao<>(FoodResource.class);
        int resourceId = (int) session.getAttribute("newResourceId");
        resource = fdao.getById(resourceId);
       logger.debug("This food resource in doget: " + resource.getName());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/addResourceOwner.jsp");
        dispatcher.forward(req, res);

    }

    /**
     * Handles HTTP POST requests.
     *
     * @param req  the HttpServletRequest object
     * @param res the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     **/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session  = req.getSession();
        String url;
        odao = new GenericDao<>(ResourceOwner.class);
        int ownerId = Integer.parseInt(req.getParameter("owner"));
        req.setAttribute("selectedOwnerId", ownerId);
//set the owner
        if(ownerId == 9999) {
            /*new owner to add, jsp should display additional fields
            ResourceOwner newOwner = new ResourceOwner();
           newOwner.setName(req.getParameter("name"));
            newOwner.setWebsite(req.getParameter("website"));
            odao.saveOrUpdate(newOwner);
*/
            url = "/admin/addResourceOwner.jsp";
        } else if(ownerId == 8888) {
            //no resource owner for this resource
            resource.setOwner(null);
            String message = "you have successfully added " + resource.getOwner().getName() + " to the resource " + resource.getName();
            session.setAttribute("successMessage", message);
            url= "/admin/ownerSuccess.jsp";
        } else {
            //choose an existing owner from the list
            resource.setOwner(odao.getById(ownerId));
            String message = "you have successfully added " + resource.getOwner().getName() + " to the resource " + resource.getName();
            session.setAttribute("successMessage", message);
            logger.debug("chose an existing owner: " + resource.getOwner().toString());
            url= "/admin/ownerSuccess.jsp";
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, res);

    }
}
