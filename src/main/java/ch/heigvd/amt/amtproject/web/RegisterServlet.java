package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") != null) {
            request.getRequestDispatcher("WEB-INF/pages/connected.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        System.out.println(request.getParameter("password-confirmation"));
        System.out.println("test");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password-confirmation");


        Object o = getServletContext().getAttribute("connectedUsers");
        HashMap<String, User> connectedUsers = o != null ? (HashMap<String, User>) o : new HashMap<String, User>();

        if (connectedUsers.containsKey(username)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User already exists.");
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Passwords don't match.");
            return;
        }

        connectedUsers.put(username, new User(username, password));
        getServletContext().setAttribute("connectedUsers", connectedUsers);

        request.getSession().setAttribute("username", username);
        request.getRequestDispatcher("WEB-INF/pages/connected.jsp").forward(request, response);
    }
}
