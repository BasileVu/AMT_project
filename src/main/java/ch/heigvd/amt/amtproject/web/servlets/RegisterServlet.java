package ch.heigvd.amt.amtproject.web.servlets;

import ch.heigvd.amt.amtproject.exception.SQLExceptionWrapper;
import ch.heigvd.amt.amtproject.exception.UserAlreadyExistingException;
import ch.heigvd.amt.amtproject.services.UserDAOLocal;
import ch.heigvd.amt.amtproject.util.Errors;
import ch.heigvd.amt.amtproject.util.FieldLength;
import ch.heigvd.amt.amtproject.util.Session;
import ch.heigvd.amt.amtproject.util.URIs;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

/**
 * Servlet handling the requests related to the register page.
 *
 * @author Benjamin Schubert and Basile Vu
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {URIs.REGISTER})
public class RegisterServlet extends HttpServlet {
    @EJB
    UserDAOLocal userDAO;

    public static final String USED_JSP = "register.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_FOLDER + USED_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password-confirmation");

        if (!parametersValid(request, response, username, password, passwordConfirmation)) {
            return;
        }

        try {
            userDAO.create(username, password, "");
        } catch (UserAlreadyExistingException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_CONFLICT,
                    Errors.USER_ALREADY_EXISTS, USED_JSP);
        } catch (SQLExceptionWrapper e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.SERVER_ERROR, USED_JSP);
            return;
        }
        Session.connectCurrentUser(request, username);
        response.sendRedirect(request.getContextPath() + URIs.ACCOUNT);
    }

    /**
     * Checks whether the parameters sent with the register request are valid. If one of the parameters is not
     * valid, an error is set, the request is forwarded to used JSP and the method returns false.
     *
     * @param request The current http request.
     * @param response The current http response.
     * @param username The username sent.
     * @param password The password sent.
     * @param passwordConfirmation The password confirmation sent.
     * @return Whether all the parameters are valid.
     * @throws ServletException
     * @throws IOException
     */
    private boolean parametersValid(HttpServletRequest request, HttpServletResponse response,
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

        return true;
    }
}
