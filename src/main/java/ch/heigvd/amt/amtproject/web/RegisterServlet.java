package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.services.SessionLocal;
import ch.heigvd.amt.amtproject.services.UserDAOLocal;
import ch.heigvd.amt.amtproject.util.Errors;
import ch.heigvd.amt.amtproject.util.FieldLength;

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
    UserDAOLocal userDAO;

    @EJB
    SessionLocal session;

    public static final String USED_JSP = "register.jsp";

    public static final String FIELD_MISSING = "All fields must be filled.";
    public static final String USER_ALREADY_EXISTS = "User already exists.";
    public static final String PASSWORD_NOT_MATCHING = "Passwords don't match.";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_FOLDER + USED_JSP).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password-confirmation");

        if (!fieldsValid(request, response, username, password, passwordConfirmation)) {
            return;
        }

        try {
            userDAO.create(new User(username, password));
        } catch (RuntimeException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, USED_JSP);
            return;
        }
        session.connectCurrentUser(request, username);
        response.sendRedirect(request.getContextPath() + "/account");
    }

    private boolean fieldsValid(HttpServletRequest request, HttpServletResponse response,
                                String username, String password, String passwordConfirmation) throws ServletException, IOException {



        if (username.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                    FIELD_MISSING, USED_JSP);
            return false;
        }

        if (!password.equals(passwordConfirmation)) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED, PASSWORD_NOT_MATCHING, USED_JSP);
            return false;
        }

        if (username.length() > FieldLength.USERNAME_MAX_LENGTH) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_BAD_REQUEST, Errors.USERNAME_TOO_LONG, USED_JSP);
            return false;
        }

        if (password.length() > FieldLength.USERNAME_MAX_LENGTH) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_BAD_REQUEST, Errors.PASSWORD_TOO_LONG, USED_JSP);
            return false;
        }

        try {
            if (userDAO.get(username) != null) {
                Errors.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                        USER_ALREADY_EXISTS, USED_JSP);
                return false;
            }
        } catch (RuntimeException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, USED_JSP);
            return false;
        }

        return true;
    }
}
