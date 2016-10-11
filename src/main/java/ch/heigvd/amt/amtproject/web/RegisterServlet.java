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
        try {
            String username = request.getParameter("username");
            userManager.createUser(
                    username,
                    request.getParameter("password"),
                    request.getParameter("password-confirmation")
            );
            userManager.connectCurrentUser(request, username);
            response.sendRedirect(request.getContextPath() + "/account");
        } catch (CreationFailedException e) {
            ErrorHandler.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage(), "register.jsp");
        }
    }
}
