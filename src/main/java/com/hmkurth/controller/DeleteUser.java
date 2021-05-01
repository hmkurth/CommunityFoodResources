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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //first you have to display the users with a checkbox indicating to delete
        GenericDao dao= new GenericDao(User.class);
        List all = dao.getAll();
        req.setAttribute("AllUsers", all);

            RequestDispatcher dispatcher = req.getRequestDispatcher("deleteUser.jsp");
            dispatcher.forward(req, resp);
        }

    }



