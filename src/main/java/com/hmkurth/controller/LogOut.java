package com.hmkurth.controller;

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
 * this servlet will be the locked down resource that prompts the tomcat login servlet,
 * redirect to the homepage after logging in
 *
 * @author hmkurth
 */
@WebServlet( name = "LogOut",
        urlPatterns = {"/logOut"}
)

public class LogOut extends HttpServlet {
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
        HttpSession session=req.getSession();
        ((HttpSession) session).invalidate();
        String url = "/admin/logoutConfirmation.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);

    }
}