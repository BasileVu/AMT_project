package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.services.SessionLocal;
import ch.heigvd.amt.amtproject.services.UserDAOLocal;
import ch.heigvd.amt.amtproject.util.Authentication;
import ch.heigvd.amt.amtproject.util.Errors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    UserDAOLocal userDAO;

    @EJB
    SessionLocal session;

    public static final String USED_JSP = "login.jsp";
    public static final String LOGIN_ERROR = "Invalid username/password combination.";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_FOLDER + USED_JSP).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean error = username == null || password == null;
        User u;

        try {
            u = userDAO.get(username);
        } catch (RuntimeException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, USED_JSP);
            return;
        }
        error = error || u == null;

        if (error || !Authentication.passwordValid(password, u)) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED, LOGIN_ERROR, USED_JSP);
            return;
        }

        session.connectCurrentUser(request, username);
        response.sendRedirect(request.getContextPath() + "/account");
    }
}
