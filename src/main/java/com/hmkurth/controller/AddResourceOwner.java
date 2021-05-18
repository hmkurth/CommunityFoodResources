package com.hmkurth.controller;


import com.hmkurth.entity.Contact;
import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.ResourceOwner;
import com.hmkurth.entity.Type;
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
@WebServlet(name = "AddResourceOwner", urlPatterns = { "/addResourceOwner" } )


public class AddResourceOwner extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao<Type> tdao;
    GenericDao<ResourceOwner> odao;
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
        listCategory(req, res);

    }

    /**
     * https://www.codejava.net/java-ee/jsp/how-to-create-dynamic-drop-down-list-in-jsp-from-database
     * As you can see, this method reads the value of the drop down list sent the client, stores it as a request’s
     * attribute, and forwards the request to the same destination page as the doGet() method.
     * Hence the listCategory() method is created to be reused by both doGet() and doPost() methods.
     * This gets the params from the form and stores them
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    private void listCategory(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session  = req.getSession();
        //get the list of owners to populate a dropdown menu for the form input
        odao = new GenericDao<>(ResourceOwner.class);
        List listOwner = odao.getAll();
        session.setAttribute("listOwner", listOwner);
        logger.debug("listOwner value from dropdown menu : " + listOwner);

        String url = "/admin/addResourceOwner.jsp";
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

        HttpSession session  = req.getSession();
        cdao = new GenericDao<>(Contact.class);
         FoodResource thisResource = (FoodResource) req.getAttribute("newResource");
        int ownerId = Integer.parseInt(req.getParameter("owner"));
        req.setAttribute("selectedOwnerId", ownerId);
//set the owner
        if(ownerId == 9999) {
            //new owner to add
            ResourceOwner newOwner = new ResourceOwner();

        } else if(ownerId == 8888) {
            //no resource owner for this resource
            thisResource.setOwner(null);
        } else {
            thisResource.setOwner(odao.getById(ownerId));
            logger.debug("chose an existing owner: " + thisResource.getOwner().toString());
        }




        //not sure i still need this
        listCategory(req, resp);

    }
}
/**



 resource.setPassword(req.getParameter("password"));
 resource.setEmail(req.getParameter("email"));
 logger.debug("Adding User: " + user);
 UserRoles role = new UserRoles();
 role.setUser(user);
 role.setUserName(user.getUserName());
 role.setRoleName("user");
 user.addRole(role);



 //set the user in a ?session variable

 if (req.getParameter("first_name").isEmpty() || (req.getParameter("last_name").isEmpty() || (req.getParameter("user_name").isEmpty() ||
 (req.getParameter("password").isEmpty() || (req.getParameter("email").isEmpty() )))))
 {
 req.setAttribute("errorMessage", "Please try again, all fields are required to sign up");
 logger.info("User Error");
 RequestDispatcher dispatcher= req.getRequestDispatcher("userSignup.jsp");
 dispatcher.include(req, resp);
 }
 else
 {
 //add the uses to the database
 // TODO check if user is already in the database
 GenericDao dao = new GenericDao(User.class);

 dao.insert(resource);

 //TOdo set the user in the session?? check if this is duplicating entries
 RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/addResourceSuccess.jsp");
 dispatcher.forward(req, resp);
 }

 /**

 **/
