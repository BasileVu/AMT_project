package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.services.SessionLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    @EJB
    SessionLocal session;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (session.isCurrentUserConnected(request)) {
            session.disconnectCurrentUser(request);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}
