package ch.heigvd.amt.amtproject.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandler {
    public static void setError(HttpServletRequest req, HttpServletResponse resp, int status,
                                String error, String dispatcherPage) throws ServletException, IOException {
        resp.setStatus(status);
        req.setAttribute("error", error);
        req.getRequestDispatcher(dispatcherPage).forward(req, resp);
    }
}
