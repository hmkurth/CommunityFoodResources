package com.hmkurth.controller;

import com.hmkurth.entity.FoodResource;
import com.hmkurth.entity.User;
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
 * this servlet will be the locked down resource that prompts the tomcat login servlet,
 * redirect to the homepage after logging in
 *
 * @author hmkurth
 */
@WebServlet(
        urlPatterns = {"/loginAction"}
)

public class LoginAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
@Override
/**
 *  Handles HTTP GET requests.
 *
 *@param  req                 the HttpServletRequest object
 *@param  res                the HttpServletResponse object
 *@exception  ServletException  if there is a Servlet failure
 *@exception IOException       if there is an IO failure
 **/
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   String url;
    GenericDao dao= new GenericDao(User.class);
    GenericDao fdao= new GenericDao(FoodResource.class);

    //set the user in the session attribute so it's available for other pages to access
    if(req.getRemoteUser() != null) {

        List all = dao.getAll();
        req.setAttribute("AllUsers", all);
        logger.info("The logged in user; " + req.getRemoteUser() + "has a role of  'user' : " + req.isUserInRole("user"));
    }
    //if the user is an admin, a special section is shown on the index,, otherwise go to index TODO delete extra code depending on how you want to direct this
    if(req.isUserInRole("admin")) {
        //load relevant info for admin home page
        List<FoodResource> unverifiedResources = fdao.getByPropertyEqualToBoolean("verificationStatus", false);
        int numberToVerify = unverifiedResources.size();
        req.setAttribute("numberToVerify", numberToVerify);;
        req.setAttribute("unverifiedResources", unverifiedResources);
        url="/admin/adminHome.jsp";

      } else {
        url="/index.jsp";
    }

    req.getSession().setAttribute("loggedInUser", req.getRemoteUser());

    RequestDispatcher dispatcher = req.getRequestDispatcher(url);
    dispatcher.forward(req, resp);

    }
}