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
 * a servlet to intake a users id from the user list and delete from database
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "DeleteUserAction", urlPatterns = { "/deleteUserAction" } )
public class DeleteUserAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao= new GenericDao(User.class);
        //get user by id
       String idToDelete = req.getParameter("delete") ;
       int id =Integer.parseInt(idToDelete);
        User userToDelete;
        userToDelete = (User) dao.getById(id);
        dao.delete(userToDelete);



        RequestDispatcher dispatcher = req.getRequestDispatcher("deleteUser.jsp");
        dispatcher.forward(req, resp);
    }

}
