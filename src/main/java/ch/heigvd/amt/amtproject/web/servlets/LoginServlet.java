package ch.heigvd.amt.amtproject.web.servlets;

import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.services.UserDAOLocal;
import ch.heigvd.amt.amtproject.util.Authentication;
import ch.heigvd.amt.amtproject.util.Errors;
import ch.heigvd.amt.amtproject.util.Session;
import ch.heigvd.amt.amtproject.util.URIs;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

/**
 * Servlet handling the requests related to the login page.
 *
 * @author Benjamin Schubert and Basile Vu
 */
@WebServlet(name = "LoginServlet", urlPatterns = {URIs.LOGIN})
public class LoginServlet extends HttpServlet {
    @EJB
    UserDAOLocal userDAO;

    public static final String USED_JSP = "login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_FOLDER + USED_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean error = username == null || password == null;
        User u;

        try {
            u = userDAO.get(username);
        } catch (SQLException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, USED_JSP);
            return;
        }
        error = error || u == null;

        if (error || !Authentication.passwordValid(password, u)) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED, Errors.LOGIN_FAILED, USED_JSP);
            return;
        }

        Session.connectCurrentUser(request, u.getUsername());
        response.sendRedirect(request.getContextPath() + URIs.ACCOUNT);
    }
}
