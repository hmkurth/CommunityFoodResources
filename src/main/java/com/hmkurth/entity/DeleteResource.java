package com.hmkurth.entity;

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
 * this servlet will delete a resource
 */
//TODO error handling!  check and redirect!!!
@WebServlet(name = "DeleteResource", urlPatterns = { "/deleteResource" } )


public class DeleteResource extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        GenericDao dao = new GenericDao(FoodResource.class);
        logger.debug("resource to edit : " + req.getAttribute("resourceToEdit"));
        FoodResource resourceToDelete = (FoodResource) session.getAttribute("newResource");
    logger.debug("resource to delete : " + resourceToDelete);
        if (req.getParameter("confirmDelete") != null) {
            dao.delete(resourceToDelete);
            String message = "You have deleted the resource: " + resourceToDelete.getName() + " .";
            req.setAttribute("message", message);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/adminHome.jsp");
        dispatcher.forward(req, resp);
    }

    }

