package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.util.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private final Session session = new Session();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session.setup(request, response);
        session.forward("register.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session.setup(request, response);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password-confirmation");

        if (username.equals("") || password.equals("") || passwordConfirmation.equals("")) {
            session.setError(HttpServletResponse.SC_UNAUTHORIZED, "All the fields must be filled.", "register.jsp");
            return;
        }

        Object o = getServletContext().getAttribute("connectedUsers");
        HashMap<String, User> connectedUsers = o != null ? (HashMap<String, User>) o : new HashMap<String, User>();

        if (connectedUsers.containsKey(username)) {
            session.setError(HttpServletResponse.SC_UNAUTHORIZED, "User already exists.", "register.jsp");
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            session.setError(HttpServletResponse.SC_UNAUTHORIZED, "Passwords don't match.", "register.jsp");
            return;
        }

        connectedUsers.put(username, new User(username, password));
        getServletContext().setAttribute("connectedUsers", connectedUsers);

        session.connectUser(username);
        session.forward("account.jsp");
    }
}
