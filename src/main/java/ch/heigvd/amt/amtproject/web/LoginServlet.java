package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.exceptions.InvalidCredentialsException;
import ch.heigvd.amt.amtproject.services.UserManager;
import ch.heigvd.amt.amtproject.services.UserManagerLocal;
import ch.heigvd.amt.amtproject.util.ErrorHandler;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    UserManagerLocal userManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_FOLDER + "login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            userManager.connectUser(response, request.getParameter("username"), request.getParameter("password"));
        } catch (InvalidCredentialsException e) {
            ErrorHandler.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage(), "login.jsp");
        }
        request.getRequestDispatcher(JSP_FOLDER + "account.jsp").forward(request, response);
    }
}
