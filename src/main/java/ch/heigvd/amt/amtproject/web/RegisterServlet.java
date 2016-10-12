package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.exceptions.CreationFailedException;
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

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    @EJB
    UserManagerLocal userManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_FOLDER + "register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password-confirmation");

        try {
            if (username.equals("") || password.equals("") || passwordConfirmation.equals("")) {
                throw new CreationFailedException("All the fields must be filled.");
            }

            if (userManager.userExists(username)) {
                throw new CreationFailedException("User already exists.");
            }

            if (!password.equals(passwordConfirmation)) {
                throw new CreationFailedException("Passwords don't match.");
            }

        } catch (CreationFailedException e) {
            ErrorHandler.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage(), "register.jsp");
            return;
        }

        userManager.createUser(username, password);
        userManager.connectCurrentUser(request, username);
        response.sendRedirect(request.getContextPath() + "/account");
    }
}
