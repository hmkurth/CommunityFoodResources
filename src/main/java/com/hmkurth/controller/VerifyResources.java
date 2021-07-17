package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.Location;
import com.hmkurth.persistence.GenericDao;
import com.hmkurth.persistence.LocationApiDao;
import lombok.SneakyThrows;
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
 * a servlet to confirm a food resource to the database
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "VerifyResources", urlPatterns = { "/verifyResources" } )


public class VerifyResources extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<FoodResource> fdao = new GenericDao<>(FoodResource.class);
    FoodResource resource;
    List<FoodResource> resourcesToVerify;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         resourcesToVerify = fdao.getByPropertyEqualToBoolean("verificationStatus", false);;//get the unverified resource from the previous request
        req.setAttribute("resourcesToVerify", resourcesToVerify);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/verifyResources.jsp");
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
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String url = "/admin/verifyResources.jsp";
        String x = req.getParameter("submit");
        LocationApiDao ldao = new LocationApiDao();

//TODo identify which resource
        String resourceId = req.getParameter("thisResource");
        req.setAttribute("selectedResourceId", resourceId);//for selection in the dropdown menu
        if(resourceId != null) {
            int thisResourceId = Integer.parseInt(resourceId);
            logger.debug("options value : " + req.getParameter("confirmVerify"));
            resource = fdao.getById(thisResourceId);
            Location thisLocation = resource.getLocation();
            // ldao.addMarker(thisLocation);//this sets/creates a marker in a map, but then what??

            //set the resource in attribute for editing by other controllers
            session.setAttribute("newResource", resource);
        }

        if (x != null && x.equals("Next")) {
            //set a boolean attribute to indicate to other controllers whether its an edit(so save/update instead of insert
            session.setAttribute("isEdited",true);
            req.setAttribute("resourceToEdit", resource);
            String message;
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
                case "editResource":
                    url = "/admin/editResources.jsp";
                    break;
                case "deleteResource":
                    message = "are you sure you want to delete  food resource, " + resource.getName() + "?";
                    url = "/admin/deleteResource.jsp";
                    break;
            }
        }




        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, res);


    }
}