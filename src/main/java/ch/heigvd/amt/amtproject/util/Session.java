package ch.heigvd.amt.amtproject.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Session {
    private HttpServletRequest request;
    private HttpServletResponse response;

    private static final String JSP_FOLDER = "WEB-INF/pages/";
    private static final String ERROR_KEY = "error";
    private static final String USERNAME_KEY = "username";

    public void setup(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void connectUser(String username) {
        request.getSession().setAttribute(USERNAME_KEY, username);
    }

    public boolean userConnected() {
        return request.getSession().getAttribute(USERNAME_KEY) != null;
    }

    public void forward(String filename) throws IOException, ServletException {
        request.getRequestDispatcher(JSP_FOLDER + filename).forward(request, response);
    }

    public void setError(int status, String error, String dispatcherFilename) throws ServletException, IOException {
        response.setStatus(status);
        request.setAttribute(ERROR_KEY, error);
        request.getRequestDispatcher(JSP_FOLDER + dispatcherFilename).forward(request, response);
    }

    public String getCurrentPath() {
        return request.getRequestURI().substring(request.getContextPath().length());
    }
}
