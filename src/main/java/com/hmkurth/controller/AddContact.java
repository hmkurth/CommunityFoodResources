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
 * a servlet to add a contact to a food resource to the database
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "AddContact", urlPatterns = { "/addContact" } )


public class AddContact extends HttpServlet {

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
        String url = null;
        Contact thisContact;
        resource = (FoodResource) session.getAttribute("newResource"); //get the unsaved resource from the previous request
        logger.info("forwarded food resource = " + resource);
        fdao = new GenericDao<FoodResource>(FoodResource.class);
        cdao = new GenericDao<Contact>(Contact.class);
        String contactId = req.getParameter("contact");
        req.setAttribute("selectedContactId", contactId);//for selection in the dropdown menu
        if(contactId != null) {
            int contactInt = Integer.parseInt(contactId);
            logger.debug("contactInt = : " + contactInt);

            //set the owner
            if (contactInt == 9999) {
                //new contact to add, jsp should display additional fields after the first submit is processed, so redirect
                url = "/admin/addContact.jsp";
            } else if (contactInt == 8888) {
                //resource is private, default set in database, id = 8888
                thisContact= cdao.getById(8888);
                logger.debug("thiscontact before setting to resource: " + thisContact);
                cdao.insert(thisContact);
                resource.setContactId(thisContact);
                fdao.insert(resource);
                String message = "you have chosen not to add a contact to the resource " + resource.getName();
                session.setAttribute("message", message);
                url = "/admin/addResourceSuccess.jsp";
            } else {
                //choose an existing contact from the list
                thisContact = cdao.getById(contactInt);
                cdao.insert(thisContact);
                resource.setContactId(thisContact);
                fdao.insert(resource);
                String message = "you have successfully added thecontact, " + resource.getContactId().getFirstName() + " to the resource " + resource.getName();
                session.setAttribute("message", message);
                logger.debug("chose an existing owner: " + resource.getContactId().toString());
                url = "/admin/addResourceSuccess.jsp";

            }//end else
        }//end if int not null, todo what if it is null can it be null if they hit submit??



        //if new owner is selected, now grab details from the second form
        String x = req.getParameter("submit2");
        if ( x != null && x.equals("Next")) {
            thisContact = new Contact();
            thisContact.setFirstName(req.getParameter("firstName"));
            thisContact.setLastName(req.getParameter("lastName"));
            thisContact.setEmail(req.getParameter("email"));
            thisContact.setPhone(req.getParameter("phone"));

            cdao.insert(thisContact);
            resource.setContactId(thisContact);
            fdao.insert(resource);
            String message = "you have successfully added the contact" + resource.getContactId().toString() + " to the resource " + resource.getName() + ". " ;
            session.setAttribute("message", message);
            url = "/admin/addResourceSuccess.jsp";
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, res);


    }
}