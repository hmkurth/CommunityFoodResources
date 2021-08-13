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
@WebServlet(name = "AddResource", urlPatterns = { "/addResource" } )


public class AddResource extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<Type> tdao = new GenericDao<>(Type.class);
    GenericDao<FoodResource> fdao;
    GenericDao<ResourceOwner> odao;
    GenericDao<Location> ldao;
    GenericDao<Contact> cdao;

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
        String message;
        List<Type> listType = tdao.getAll();
        session.setAttribute("listType", listType);
        //set boolean to indicate if it's an edited resource
       // session.setAttribute("isEdited", false);
        //get the resource to edit (from verifyResource)
        if(!(req.getAttribute("resourceToEdit") == null)){
            FoodResource resource = (FoodResource) req.getAttribute("resourceToEdit");
            session.setAttribute("newResource", resource);
            req.setAttribute("newResource", resource);
            message = "Editing Food Resource: " + req.getAttribute("resourceToEdit");
            req.setAttribute("message", message);

        } else {
            session.setAttribute("newResource", null);
            req.setAttribute("newResource", null);
        }


        //clear existing info if present
        if(req.getParameter("clear") !=null) {
           session.setAttribute("newResource", null);
            req.setAttribute("newResource", null);

        }


        listCategory(req, res);

    }

    /**
     * https://www.codejava.net/java-ee/jsp/how-to-create-dynamic-drop-down-list-in-jsp-from-database
     * As you can see, this method reads the value of the drop down list sent the client, stores it as a request’s
     * attribute, and forwards the request to the same destination page as the doGet() method.
     * Hence the listCategory() method is created to be reused by both doGet() and doPost() methods.
     * This gets the params from the form and stores them
     *
     * @param req, the request
     * @param res, the response
     * @throws ServletException
     * @throws IOException
     */
    private void listCategory(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        //get the list of owners to populate a dropdown menu for the form input
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

        String url = "/admin/addResource.jsp";
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
        String url = "admin/addResourceOwner.jsp";
        FoodResource resource;
        HttpSession session = req.getSession();
        int typeId;

            resource = new FoodResource();
            req.setAttribute("newResource", resource);
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
        if(req.getParameter("type") != null) {
            typeId = Integer.parseInt(req.getParameter("type"));
            req.setAttribute("selectedTypeId", typeId);
            Type thisType = tdao.getById(typeId);
            resource.setTypeId(thisType);
            thisType.addResource(resource);
            //tdao.saveOrUpdate(thisType);//saves the resource to list in types, but giving me a constraint violation TODO
        }


        logger.debug("Resource at 'confirm: : " + resource.toString());
        String x = req.getParameter("submit");
        if (x != null && x.equals("confirm")) {
            session.setAttribute("newResourceId", resource.getId());
            session.setAttribute("newResource", resource);
            //forward this resource id to add owner, contact, or location servlets
          //  url = "/addResourceOwner";//to the jsp or servlet?????

        }
         RequestDispatcher dispatcher = req.getRequestDispatcher(url);
         dispatcher.forward(req, resp);

       //listCategory(req, resp);
    }

}

