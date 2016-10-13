package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.dao.UserDAO;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.services.SessionLocal;
import ch.heigvd.amt.amtproject.util.ErrorHandler;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @EJB
    UserDAO userDAO;

    @EJB
    SessionLocal session;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_FOLDER + "register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password-confirmation");

        if (username.equals("") || password.equals("") || passwordConfirmation.equals("")) {
            ErrorHandler.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                    "All the fields must be filled.", "register.jsp");
            return;
        }

        if (userDAO.get(username) != null) {
            ErrorHandler.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                    "User already exists.", "register.jsp");
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            ErrorHandler.setErrorAndForward(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Passwords don't match.", "register.jsp");
            return;
        }

        userDAO.create(new User(username, password));
        session.connectCurrentUser(request, username);
        response.sendRedirect(request.getContextPath() + "/account");
    }
}
