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

            RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteUser.jsp");
            dispatcher.forward(req, resp);
        }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao= new GenericDao(User.class);
        //get user by id
        String idToDelete = req.getParameter("delete") ;
        int id =Integer.parseInt(idToDelete);
        User userToDelete;
        userToDelete = (User) dao.getById(id);


        // confirm delete make a message/popup? Redirect to ???
        if (req.getParameter("submit") != null) {
            // Delete button is pressed, send confirmation message to be displayed through EL in jsp
            String message = "Are you sure you want to delete user: " + userToDelete.getFirstName() + " " + userToDelete.getLastName() + " ?";
            req.setAttribute("message", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("deleteUser.jsp");
            dispatcher.forward(req, resp);
        }
        //if confirmDelete is pressed, delete from database
        if (req.getParameter("confirmDelete") != null) {
            // Delete button is pressed.
            dao.delete(userToDelete);
            String message = "You have deleted user: " + userToDelete.getFirstName() + " " + userToDelete.getLastName() + " .";
            req.setAttribute("message", message);
            //TODO redirect to ??
            RequestDispatcher dispatcher = req.getRequestDispatcher("deleteUser.jsp");
            dispatcher.forward(req, resp);
        }



        RequestDispatcher dispatcher = req.getRequestDispatcher("deleteUser.jsp");
        dispatcher.forward(req, resp);
    }


}



