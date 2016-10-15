package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.dao.UserDAO;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.services.SessionLocal;
import ch.heigvd.amt.amtproject.util.Errors;

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
    UserDAO userDAO;

    @EJB
    SessionLocal session;

    public static String usedJSP = "register.jsp";

    public static String fieldMissingErrorMessage = "All fields must be filled.";
    public static String userAlreadyExistsErrorMessage = "User already exists.";
    public static String passwordsNotMatchingErrorMessage = "Passwords don't match.";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_FOLDER + usedJSP).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password-confirmation");

        if (username.equals("") || password.equals("") || passwordConfirmation.equals("")) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                    fieldMissingErrorMessage, usedJSP);
            return;
        }

        try {
            if (userDAO.get(username) != null) {
                Errors.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                        userAlreadyExistsErrorMessage, usedJSP);
                return;
            }
        } catch (RuntimeException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, usedJSP);
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                    passwordsNotMatchingErrorMessage, usedJSP);
            return;
        }

        try {
            userDAO.create(new User(username, password));
        } catch (RuntimeException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, usedJSP);
            return;
        }
        session.connectCurrentUser(request, username);
        response.sendRedirect(request.getContextPath() + "/account");
    }
}
