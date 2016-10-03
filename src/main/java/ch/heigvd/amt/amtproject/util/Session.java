package ch.heigvd.amt.amtproject.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Session {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public void setup(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public boolean userConnected() {
        return request.getSession().getAttribute("username") != null;
    }

    public void forward(String filename) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/pages/" + filename).forward(request, response);
    }

    public String getCurrentPath() {
        return request.getRequestURI().substring(request.getContextPath().length());
    }
}
