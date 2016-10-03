package ch.heigvd.amt.amtproject.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandler {
    public static void setError(HttpServletRequest request, HttpServletResponse response, int errorCode,
                                String error, String dispatcherPage) throws ServletException, IOException {
        response.setStatus(errorCode);
        request.setAttribute("error", error);
        request.getRequestDispatcher(dispatcherPage).forward(request, response);
    }
}
