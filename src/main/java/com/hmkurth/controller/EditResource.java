package com.hmkurth.controller;


import com.hmkurth.entity.*;
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
 * a servlet to add a food resource to the database
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "EditResource", urlPatterns = { "/editResource" } )


public class EditResource extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<Type> tdao = new GenericDao<>(Type.class);
    GenericDao<FoodResource> fdao = new GenericDao<>(FoodResource.class);
    GenericDao<ResourceOwner> odao;
    GenericDao<Location> ldao;
    GenericDao<Contact>  cdao = new GenericDao<>(Contact.class);
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
        HttpSession session = req.getSession();

//get the list of entities  to populate a dropdown menu for the form input
        odao = new GenericDao<>(ResourceOwner.class);
        List<ResourceOwner> listOwner = odao.getAll();
        session.setAttribute("listOwner", listOwner);
        logger.debug("listOwner value from dropdown menu : " + listOwner);
        //get the list of types of resources to populate a dropdown menu for the form input
        tdao = new GenericDao<>(Type.class);
        List<Type> listType = tdao.getAll();
        session.setAttribute("listType", listType);
        logger.debug("list type : " + listType);
        //get the list of contact to populate a dropdown menu for the form input
        cdao = new GenericDao<>(Contact.class);
        List<Contact> listContact = cdao.getAll();
        session.setAttribute("listContact", listContact);
        logger.debug("list contact : " + listContact);
        fdao = new GenericDao<>(FoodResource.class);
        List<FoodResource> listAll = fdao.getAll();
        session.setAttribute("listAll", listAll);
        ldao = new GenericDao<>(Location.class);
        List<Location> listLocation = ldao.getAll();
        session.setAttribute("allLocations", listLocation);

        //select a resource, either continuing from the verify screen, choosing from the dropdown menu,
        //first check to see if the resource was set in verify
        if (!(req.getAttribute("resourceToEdit") == null)) {
            resource = (FoodResource) req.getAttribute("resourceToEdit");
            session.setAttribute("newResource", resource);
            logger.debug("editing this resource, from the req.attribute resourceToEdit: " + resource);
        } else if (req.getParameter("resourceToEdit") != null) {
            String resourceId = req.getParameter("resourceToEdit");
            if (resourceId != null) {
                req.setAttribute("selectedResourceId", resourceId);//for selection in the dropdown menu
                int intId = Integer.parseInt(resourceId);
                resource = fdao.getById(intId);
                session.setAttribute("newResource", resource);
            }
        }


        logger.debug("resource to Edit, from newResource session attribute " +session.getAttribute("newResource"));
/*set session attributes for further editing, this creates errors
        Location location = resource.getLocation();
        session.setAttribute("location", location);
        Contact contact = resource.getContactId();
        session.setAttribute("contact", contact);
        ResourceOwner owner = resource.getOwner();
        session.setAttribute("owner", owner);
*/


                String url = "/admin/editResources.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);


    }

    /**
     * Handles HTTP POST requests.
     *
     * @param req  the HttpServletRequest object
     * @param resp the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     **/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/admin/editResources.jsp";
        String message;
        HttpSession session = req.getSession();
      resource= (FoodResource) session.getAttribute("newResource");
        tdao = new GenericDao<>(Type.class);
        List<Type> listType = tdao.getAll();
        session.setAttribute("listType", listType);
        int typeId;
logger.debug("resource at doPost: " + resource);
                //get params
                resource.setName(req.getParameter("name"));
                resource.setDescription(req.getParameter("description"));
                resource.setServiceArea(req.getParameter("serviceArea"));
                resource.setWebsite(req.getParameter("website"));
                resource.setDocumentation(req.getParameter("documentation"));//validate null responses, 'required' attribute not working
                resource.setDaysOfWeek(req.getParameter("days"));
                resource.setDeliveryOffered(Boolean.parseBoolean(req.getParameter("deliveryB")));
                resource.setDeliveryDescription(req.getParameter("deliveryDescription"));
                resource.setDietaryConsiderations(req.getParameter("dietary"));
                resource.setComments(req.getParameter("comments"));


                //sets the values from dropdown menus
                if (req.getParameter("type") != null) {
                    typeId = Integer.parseInt(req.getParameter("type"));
                    req.setAttribute("selectedTypeId", typeId);
                    Type thisType = tdao.getById(typeId);
                    resource.setTypeId(thisType);
                    thisType.addResource(resource);
                    //tdao.saveOrUpdate(thisType);//saves the resource to list in types, but giving me a constraint violation TODO
                }

        String x = req.getParameter("submit2");
        if (x != null && x.equals("Next")) {
                logger.debug("Resource at 'confirm: : " + resource.toString());
                //set session attributes for further editing
                Location location = resource.getLocation();
                session.setAttribute("location", location);
                Contact contact = resource.getContactId();
                session.setAttribute("contact", contact);
                ResourceOwner owner = resource.getOwner();
                session.setAttribute("owner", owner);
                fdao.saveOrUpdate(resource);
                switch (req.getParameter("confirmVerify")) {
                    case "addData":
                        resource.setVerificationStatus(true);
                        //save or update

                        message = "you have successfully verified a  food resource, " + resource.getName() + ".";
                        //todo show map location and confirm that
                        req.setAttribute("message", message);
                        //reset the number of unverified resources
                        req.setAttribute("numberToVerify", fdao.getByPropertyEqualToBoolean("verificationStatus", false).size());
                        url = "/admin/adminHome.jsp";
                        break;
                    case "addLocation":
                        session.setAttribute("location", location);
                        url = "/admin/addLocation.jsp";
                        break;
                    case "addContact":
                        session.setAttribute("contact", contact);
                        url = "/admin/addContact.jsp";
                        break;
                    case "addResourceOwner":
                        session.setAttribute("owner", owner);
                        url = "/admin/addResourceOwner.jsp";
                        break;
                    case "deleteResource":
                        message = "are you sure you want to delete  food resource, " + resource.getName() + "?";
                        req.setAttribute("message", message);
                        url = "/admin/deleteResource.jsp";
                        break;
                }

            }

            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
        }


    }
