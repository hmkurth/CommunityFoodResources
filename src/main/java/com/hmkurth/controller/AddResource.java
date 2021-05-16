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
import java.io.IOException;
import java.util.List;

/**
 * a servlet to add a food resource to the database
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "AddResource", urlPatterns = { "/addResource" } )


public class AddResource extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                 the HttpServletRequest object
     *@param  res                the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        GenericDao dao = new GenericDao(ResourceOwner.class);
        List<ResourceOwner> listOwner = dao.getAll();
        req.setAttribute("listOwner", listOwner);
    //do i really want to list out contacts and locations, more often you would be adding new ones...
        GenericDao cdao = new GenericDao(Contact.class);
        List<Contact> listContact = cdao.getAll();
        req.setAttribute("listContact", listContact);




        String url = "/addResource.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, res);

    }
    /**
     *  Handles HTTP POST requests.
     *
     *@param  req                 the HttpServletRequest object
     *@param  resp                the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     **/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo change for food resources
        FoodResource resource = new FoodResource();

        resource.setName(req.getParameter("name"));
        //resource.setTypeId(req.getParameter("type"));
        logger.debug("type: " + req.getParameter("type"));
        int typeId = Integer.parseInt(req.getParameter("type"));
        req.setAttribute("typeId", typeId);
        logger.debug("Adding Type: " + resource.getTypeId());
    /**    resource.setPassword(req.getParameter("password"));
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
     **/
            //TOdo set the user in the session?? check if this is duplicating entries
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/addResourceSuccess.jsp");
            dispatcher.forward(req, resp);
        }

    }


