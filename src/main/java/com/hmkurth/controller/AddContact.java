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
import java.util.List;

/**
 * a servlet to add a contact to a food resource to the database
 * this will also need to retrieve values for editing, confirming, and verifying the resource, set the drop down list for allContacts
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "AddContact", urlPatterns = { "/addContact" } )


public class AddContact extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<FoodResource>  fdao = new GenericDao<>(FoodResource.class);
    GenericDao<ResourceOwner>  odao = new GenericDao<>(ResourceOwner.class);
    GenericDao<Contact>  cdao = new GenericDao<>(Contact.class);
    FoodResource resource;
    Contact contact;
    List<Contact> listContact = cdao.getAll();
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
        String url = "/addContact.jsp";
        session.setAttribute("listContact", listContact);
        resource = (FoodResource) session.getAttribute("newResource"); //get the unsaved resource from the previous request

        String contactId = req.getParameter("contact");
        req.setAttribute("selectedContactId", contactId);//for selection in the dropdown menu
        if(contactId != null) {
            int contactInt = Integer.parseInt(contactId);
            //set the contact
            if (contactInt == 9999) {
                //new contact to add, jsp should display additional fields after the first submit is processed, so redirect
                contact = new Contact();

            } else if (contactInt == 8888) {
                resource.setContactId(null);
                String message = "you have chosen not to add a contact to the resource " + resource.getName();
                session.setAttribute("message", message);
                url = "/confirmResource.jsp";
            }else if (contactInt == 2222) {
                contact = resource.getContactId();
                session.setAttribute("contact", contact);

            } else {
                //choose an existing contact from the list
                contact = cdao.getById(contactInt);
                resource.setContactId(contact);
                session.setAttribute("contact", contact);
                String message = "you have successfully added the contact, " + resource.getContactId().getFirstName() + " to the resource " + resource.getName();
                session.setAttribute("message", message);
                url = "/confirmResource.jsp";

            }//end else
            session.setAttribute("contact", contact);


        }//end if int not null,
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
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
        String url = "/addContact.jsp";
        String message = "";
        String x1 = req.getParameter("submit");
            contact= (Contact) session.getAttribute("contact");
            resource = (FoodResource) session.getAttribute("newResource"); //get the unsaved resource from the previous request
            //i keep getting nulls on these values, so i'm trying to find a way around that
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("website");
            String phone = req.getParameter("phone");


            logger.debug("doPost firstname = " + firstName);

            //if new contact is selected, now grab details from the second form
            String x = req.getParameter("submit2");
            logger.debug("x at submit2: " + x);
            if (x != null ) {
                if (contact.getLastName() != null) {//it's a save/update
                    contact = (Contact) session.getAttribute("contact");
                    contact.setFirstName(firstName);
                    contact.setLastName(lastName);
                    contact.setEmail(email);
                    contact.setPhone(phone);
                    message = "you have successfully edited/ updated this contact";
                    url = "admin/editResources.jsp";
                } else {//it's a brand new resource

                    logger.debug("add new contact, should be null, contact = " + contact);
                    contact.setFirstName(firstName);
                    contact.setLastName(lastName);
                    contact.setEmail(email);
                    contact.setPhone(phone);
                    logger.debug("in submit2 block, contact first name:" + contact.getFirstName());
                   // cdao.insert(contact);// trying this b/c was adding twice
                   // cdao.saveOrUpdate(contact);
                    // fdao.insert(resource);  when i had this here it was adding to my db twice
                    message = "you have successfully added the contact" + resource.getContactId().toString() + " to the resource " + resource.getName() + ". ";
                    url = "/confirmResource.jsp";
                }
                resource.setContactId(contact);
                cdao.saveOrUpdate(contact); //adding twice so removing, NOT,
                fdao.saveOrUpdate(resource);

                session.setAttribute("message", message);
                RequestDispatcher dispatcher = req.getRequestDispatcher(url);
                dispatcher.forward(req, res);
            }


            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, res);

        }
    }
