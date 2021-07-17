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
    GenericDao<Type> tdao;
    GenericDao<FoodResource> fdao;
    GenericDao<ResourceOwner> odao;
    GenericDao<Location> ldao;
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
        HttpSession session = req.getSession();
        fdao = new GenericDao<>(FoodResource.class);
        tdao = new GenericDao<>(Type.class);
        List<Type> listType = tdao.getAll();
        session.setAttribute("listType", listType);

        //select a resource, either continuing from the verify screen, choosing from the dropdown menu,
        //first check to see if the resource was set in verify
        if (!(req.getAttribute("resourceToEdit") == null)) {
            resource = (FoodResource) req.getAttribute("resourceToEdit");
            logger.debug("editing this resource: " + resource);

        } else if (req.getParameter("resourceToEdit") != null) {
            String resourceId = req.getParameter("resourceToEdit");
            if (resourceId != null) {
                req.setAttribute("selectedResourceId", resourceId);//for selection in the dropdown menu
                int intId = Integer.parseInt(resourceId);
                resource = fdao.getById(intId);
                req.setAttribute("newResource", resource);
                logger.debug("resource to Edit " + req.getAttribute("resourceToEdit"));
            }
        }
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
      // resource= (FoodResource) req.getAttribute("newResource");
        tdao = new GenericDao<>(Type.class);
        List<Type> listType = tdao.getAll();
        session.setAttribute("listType", listType);
        int typeId;

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

                switch (req.getParameter("confirmVerify")) {
                    case "addData":
                        resource.setVerificationStatus(true);
                        //save or update
                        fdao.saveOrUpdate(resource);
                        message = "you have successfully verified a  food resource, " + resource.getName() + ".";
                        //todo show map location and confirm that
                        req.setAttribute("message", message);
                        //reset the number of unverified resources
                        req.setAttribute("numberToVerify", fdao.getByPropertyEqualToBoolean("verificationStatus", false).size());
                        url = "/admin/adminHome.jsp";
                        break;
                    case "addLocation":
                        url = "/admin/addLocation.jsp";
                        break;
                    case "addContact":
                        url = "/admin/addContact.jsp";
                        break;
                    case "addResourceOwner":
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
