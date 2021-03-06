package com.hmkurth.controller;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * a servlet to intake a users id from the req attribute and delete from database,
 * gives user a confirmation name and submit
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "DeleteUserAction", urlPatterns = { "/deleteUserAction" } )
public class DeleteUserAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                 the HttpServletRequest object
     *@param  resp                the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao dao= new GenericDao(User.class);
        User userToDelete = (User) req.getAttribute("userToDelete");
        logger.debug("In deleteAction doGet, userToDelete: " + userToDelete);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/deleteUserConfirmation.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    /**
     *  Handles HTTP POST requests.
     *
     *@param  req                 the HttpServletRequest object
     *@param  resp                the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     **/
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        GenericDao dao = new GenericDao(User.class);
        User userToDelete = (User) session.getAttribute("userToDelete");
        logger.debug("parameter confirmDelete :" + req.getParameter("confirmDelete") );

        if (req.getParameter("confirmDelete") != null) {
            dao.delete(userToDelete);
            String message = "You have deleted user: " + userToDelete.getFirstName() + " " + userToDelete.getLastName() + " .";
            req.setAttribute("message", message);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/adminHome.jsp");
        dispatcher.forward(req, resp);
    }

}
