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
 * a servlet to confirm a food resource to the database
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "ConfirmResource", urlPatterns = { "/confirmResource" } )


public class ConfirmResource extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<FoodResource> fdao;
    GenericDao<ResourceOwner> odao;
    GenericDao<Contact> cdao;
    FoodResource resource;

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



//todo show map location and confirm that

        Contact thisContact;
        resource = (FoodResource) session.getAttribute("newResource"); //get the unsaved resource from the previous request
        logger.info("forwarded food resource = " + resource);
        fdao = new GenericDao<FoodResource>(FoodResource.class);
        cdao = new GenericDao<Contact>(Contact.class);

        fdao.insert(resource);



        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/confirmSuccess.jsp");
        dispatcher.forward(req, res);


    }
}