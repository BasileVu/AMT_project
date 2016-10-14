package ch.heigvd.amt.amtproject.util;

import javax.ejb.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

@Singleton
public class Errors {
    public static final String JSP_ERROR_KEY = "error";
    public static final String CLIENT_500 = "An error occurred at server side. Please retry later.";

    public static void setErrorAndForward(HttpServletRequest request, HttpServletResponse response,
                                          int status, String error, String dispatcherFilename) throws ServletException, IOException {
        response.setStatus(status);
        request.setAttribute(JSP_ERROR_KEY, error);
        request.getRequestDispatcher(JSP_FOLDER + dispatcherFilename).forward(request, response);
    }
}
