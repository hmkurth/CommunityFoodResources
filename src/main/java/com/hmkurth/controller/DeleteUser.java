package com.hmkurth.controller;


import com.hmkurth.entity.User;
import com.hmkurth.entity.UserRoles;
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
 * this servlet will forward the user list to the jsp so the id can be chosen for deletion
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "DeleteUser", urlPatterns = { "/deleteUser" } )


public class DeleteUser extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //first you have to display the users with a checkbox indicating to delete
        GenericDao dao= new GenericDao(User.class);
        List all = dao.getAll();
        req.setAttribute("AllUsers", all);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/deleteUser.jsp");
            dispatcher.forward(req, resp);
        }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        GenericDao dao= new GenericDao(User.class);
       req.setAttribute("submitted", false);
        //get user by id
        String idToDelete = req.getParameter("delete") ;
        int id =Integer.parseInt(idToDelete);
        User userToDelete;
        userToDelete = (User) dao.getById(id);
        //set the user to delete in a session or req variable to forward to the Delete Action Servlet, since it seems like I can't do it all here
        session.setAttribute("userToDelete", userToDelete);
        //req.setAttribute("userToDelete", userToDelete);
        logger.debug("user id to delete: " + id);

        // confirm delete make a message/popup? Redirect to ???  make sure the id is a valid user id
        if (req.getParameter("submit") != null) {
            logger.debug("delete submit button pushed: " );
            req.setAttribute("submitted", true);
            // Delete button is pressed, send confirmation message to be displayed through EL in jsp
            String message = "Are you sure you want to delete user: " + userToDelete.getFirstName() + " " + userToDelete.getLastName() + " ?";
            logger.debug(message);
            req.setAttribute("message", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/deleteUserConfirmation.jsp");
            dispatcher.forward(req, resp);
        }
    /**
        //CAN YOU EVEN DO BOTH OF THESE AT ONCE??
        //if confirmDelete is pressed, delete from database
        if (req.getParameter("confirmDelete") != null) {
            logger.debug("confirmDelete Button status : " + req.getParameter("confirmDelete"));
            // Delete button is pressed.
            dao.delete(userToDelete);
            String message = "You have deleted user: " + userToDelete.getFirstName() + " " + userToDelete.getLastName() + " .";
            req.setAttribute("message", message);
            //TODO redirect to ??
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/deleteUserConfirmation.jsp");
            dispatcher.forward(req, resp);
        }

**/

    }


}



