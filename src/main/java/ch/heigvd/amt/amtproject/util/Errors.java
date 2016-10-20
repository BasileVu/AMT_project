package ch.heigvd.amt.amtproject.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Regroups errors used in the application.
 *
 * @author Benjamin Schubert and Basile Vu
 */
public class Errors {
    public static final String CLIENT_500 = "An error occurred at server side. Please retry later.";

    public static final String USERNAME_TOO_LONG = "Username is too long (max " + FieldLength.USERNAME_MAX_LENGTH + " chars).";
    public static final String PASSWORD_TOO_LONG = "Password is too long (max " + FieldLength.PASSWORD_MAX_LENGTH + " chars).";
    public static final String QUOTE_TOO_LONG = "Quote is too long (max " + FieldLength.QUOTE_MAX_LENGTH + " chars).";

    public static final String USERNAME_MISSING = "Username is missing.";
    public static final String PASSWORD_MISSING = "Password is missing.";
    public static final String PASSWORD_CONFIRMATION_MISSING = "Password confirmation is missing.";

    public static final String USER_ALREADY_EXISTS = "User already exists.";
    public static final String PASSWORD_NOT_MATCHING = "Passwords don't match.";

    public static final String LOGIN_FAILED = "Invalid username/password combination.";

    /**
     * Set the error attribute to the given value, forward the request to the given JSP and set the response status
     * code to the given value.
     *
     * @param request The current http request.
     * @param response The current http response.
     * @param status The status code to set for the response.
     * @param error The value of the error to display in the JSP.
     * @param dispatcherFilename The name of the JSP where the request is forwarded.
     * @throws ServletException
     * @throws IOException
     */
    public static void setErrorAndForward(HttpServletRequest request, HttpServletResponse response,
                                          int status, String error, String dispatcherFilename) throws ServletException, IOException {
        response.setStatus(status);
        request.setAttribute(JSPKeys.JSP_ERROR, error);
        request.getRequestDispatcher(Paths.JSP_FOLDER + dispatcherFilename).forward(request, response);
    }
}
