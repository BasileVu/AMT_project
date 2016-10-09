package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.services.UserManagerLocal;
import ch.heigvd.amt.amtproject.util.Authentication;
import ch.heigvd.amt.amtproject.util.ErrorHandler;

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
    UserManagerLocal userManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_FOLDER + "login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean error = username == null || password == null;
        User u = userManager.get(username);
        error = error || u == null;

        if (error || !Authentication.passwordValid(password, u.getPassword())) {
            ErrorHandler.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid username/password combination.", "login.jsp");
            return;
        }

        userManager.connectCurrentUser(request, username);
        request.getRequestDispatcher(JSP_FOLDER + "account.jsp").forward(request, response);
    }
}
