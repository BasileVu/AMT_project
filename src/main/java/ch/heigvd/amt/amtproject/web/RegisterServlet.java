package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.services.UserDAOLocal;
import ch.heigvd.amt.amtproject.util.Errors;
import ch.heigvd.amt.amtproject.util.FieldLength;
import ch.heigvd.amt.amtproject.util.Session;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    @EJB
    UserDAOLocal userDAO;

    public static final String USED_JSP = "register.jsp";

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
            userDAO.create(username, password);
        } catch (SQLException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, USED_JSP);
            return;
        }
        Session.connectCurrentUser(request, username);
        response.sendRedirect(request.getContextPath() + "/account");
    }

    private boolean fieldsValid(HttpServletRequest request, HttpServletResponse response,
                                String username, String password, String passwordConfirmation) throws ServletException, IOException {

        if (username == null || username.isEmpty()) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_BAD_REQUEST,
                    Errors.USERNAME_MISSING, USED_JSP);
            return false;
        }

        if (password == null || password.isEmpty()) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_BAD_REQUEST,
                    Errors.PASSWORD_MISSING, USED_JSP);
            return false;
        }

        if (passwordConfirmation == null || passwordConfirmation.isEmpty()) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_BAD_REQUEST,
                    Errors.PASSWORD_CONFIRMATION_MISSING, USED_JSP);
            return false;
        }

        if (!password.equals(passwordConfirmation)) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_BAD_REQUEST,
                    Errors.PASSWORD_NOT_MATCHING, USED_JSP);
            return false;
        }

        if (username.length() > FieldLength.USERNAME_MAX_LENGTH) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_BAD_REQUEST,
                    Errors.USERNAME_TOO_LONG, USED_JSP);
            return false;
        }

        if (password.length() > FieldLength.USERNAME_MAX_LENGTH) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_BAD_REQUEST,
                    Errors.PASSWORD_TOO_LONG, USED_JSP);
            return false;
        }

        try {
            if (userDAO.get(username) != null) {
                Errors.setErrorAndForward(request, response, HttpServletResponse.SC_CONFLICT,
                        Errors.USER_ALREADY_EXISTS, USED_JSP);
                return false;
            }
        } catch (SQLException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, USED_JSP);
            return false;
        }

        return true;
    }
}
