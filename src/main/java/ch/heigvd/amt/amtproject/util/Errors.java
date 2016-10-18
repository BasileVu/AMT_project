package ch.heigvd.amt.amtproject.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Errors {
    public static final String CLIENT_500 = "An error occurred at server side. Please retry later.";
    public static final String USERNAME_TOO_LONG = "Username is too long (max " + FieldLength.USERNAME_MAX_LENGTH + " chars)";
    public static final String PASSWORD_TOO_LONG = "Password is too long (max " + FieldLength.PASSWORD_MAX_LENGTH + " chars)";

    public static void setErrorAndForward(HttpServletRequest request, HttpServletResponse response,
                                          int status, String error, String dispatcherFilename) throws ServletException, IOException {
        response.setStatus(status);
        request.setAttribute(Keys.JSP_ERROR, error);
        request.getRequestDispatcher(Paths.JSP_FOLDER + dispatcherFilename).forward(request, response);
    }
}
