package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.util.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password-confirmation");

        if (username.equals("") || password.equals("") || passwordConfirmation.equals("")) {
            ErrorHandler.setError(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                    "All the fields must be filled.", "WEB-INF/pages/register.jsp");
            return;
        }

        Object o = getServletContext().getAttribute("connectedUsers");
        HashMap<String, User> connectedUsers = o != null ? (HashMap<String, User>) o : new HashMap<String, User>();

        if (connectedUsers.containsKey(username)) {
            ErrorHandler.setError(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                    "User already exists.", "WEB-INF/pages/register.jsp");
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            ErrorHandler.setError(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Passwords don't match.", "WEB-INF/pages/register.jsp");
            return;
        }

        connectedUsers.put(username, new User(username, password));
        getServletContext().setAttribute("connectedUsers", connectedUsers);

        request.getSession().setAttribute("username", username);
        request.getRequestDispatcher("WEB-INF/pages/connected.jsp").forward(request, response);
    }
}
