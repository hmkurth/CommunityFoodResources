package com.hmkurth.controller;


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
@WebServlet(name = "AddResource", urlPatterns = { "/addResource" } )


public class AddResource extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

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
    logger.debug("in do get addResource");
        req.setAttribute("anything1", "anything message coming through from do get");
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
        GenericDao<ResourceOwner> dao = new GenericDao<>(ResourceOwner.class);
        List listOwner = dao.getAll();
        session.setAttribute("listOwner", listOwner);
        session.setAttribute("anything", "anything message coming through");
        logger.debug("listOwner value from dropdown menu : " + listOwner);
        //get the list of types of resources to populate a dropdown menu for the form input
        GenericDao<Type> tdao = new GenericDao<>(Type.class);
        List listType = tdao.getAll();
        session.setAttribute("listType", listType);
        logger.debug("list type : " + listType);

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
        //todo change for food resources
        HttpSession session  = req.getSession();
        int typeId = Integer.parseInt(req.getParameter("type"));
        session.setAttribute("selectedTypeId", typeId);

        int ownerId = Integer.parseInt(req.getParameter("owner"));
        session.setAttribute("selectedOwnerId", ownerId);


        listCategory(req, resp);
    }
}
        /**

         FoodResource resource = new FoodResource();

        resource.setName(req.getParameter("name"));
        //resource.setTypeId(req.getParameter("type"));
        logger.debug("type: " + req.getParameter("type"));

        logger.debug("Adding Type: " + resource.getTypeId());
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

    }


         **/