package com.hmkurth.controller;


import com.hmkurth.entity.FoodResource;
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

    private void listCategory(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session  = req.getSession();
        //get the list of unverified resources to populate a dropdown menu for the form input
        fdao = new GenericDao<>(FoodResource.class);
        List<FoodResource> resourcesToVerify = fdao.getByPropertyEqualToBoolean("verificationStatus", false);; //get the unverified resource from the previous request
        req.setAttribute("resourcesToVerify", resourcesToVerify);

        String url = "/admin/verifyResources.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);

    }
     */
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
        HttpSession session = req.getSession();
        String url = "/admin/verifyResources.jsp";
        String x = req.getParameter("submit");
//TODo identify which resource
        String resourceId = req.getParameter("thisResource");
        req.setAttribute("selectedResourceId", resourceId);//for selection in the dropdown menu
        int thisResourceId = Integer.parseInt(resourceId);
        logger.debug("options value : " + req.getParameter("confirmVerify"));
        resource = fdao.getById( thisResourceId);
        //set the resource in attribute for editing by other controllers
        session.setAttribute("newResource", resource);


        if (x != null && x.equals("Next")) {
            //set a boolean attribute to indicate to other controllers whether its an edit(so save/update instead of insert
            session.setAttribute("isEdited",true);
            req.setAttribute("resourceToEdit", resource);
            switch (req.getParameter("confirmVerify")) {
                case "addData":
                   resource.setVerificationStatus(true);
                   //save or update
                    fdao.saveOrUpdate(resource);
                    String message = "you have successfully verified a  food resource, " + resource.getName() + ".";
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
                case "addResource":
                    //todo more edit capabilities, this just starts over

                    url = "/admin/addResource.jsp";
                    break;
            }
        }




        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, res);


    }
}