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
        GenericDao dao = new GenericDao(User.class);
        List all = dao.getAll();
        req.setAttribute("AllUsers", all);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/deleteUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        GenericDao dao = new GenericDao(User.class);
        req.setAttribute("submitted", false);
        //get user by id
        String idToDelete = req.getParameter("delete");
        int id;
        User userToDelete;

        //check for null to get integer?
        if (idToDelete != null) {
            id = Integer.parseInt(idToDelete);
            //make sure that it is a valid id number
            User retrievedUser = (User) dao.getById(id);
            if (retrievedUser != null) {
                userToDelete = (User) dao.getById(id);
                //set the user to delete in a session or req variable to forward to the Delete Action Servlet, since it seems like I can't do it all here
                session.setAttribute("userToDelete", userToDelete);
                req.setAttribute("userToDelete", userToDelete);
                logger.debug("user id to delete: " + id);


            // confirm delete make a message/popup? Redirect to ???  make sure the id is a valid user id
            if (req.getParameter("submit") != null) {
                req.setAttribute("submitted", true);
                // Delete button is pressed, send confirmation message to be displayed through EL in jsp
                String message = "Are you sure you want to delete user: " + userToDelete.getFirstName() + " " + userToDelete.getLastName() + " ?";
                req.setAttribute("message", message);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/deleteUser.jsp");
                dispatcher.forward(req, resp);
            }

            } else {
                //error message todo
                String errorMessage = "That is not a valid user id, try again";
                req.setAttribute("errorMessage", errorMessage);

            }

        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/deleteUser.jsp");
        dispatcher.forward(req, resp);

    }

}

