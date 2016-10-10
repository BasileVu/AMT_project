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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private final Session session = new Session();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session.setup(request, response);
        session.forward("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session.setup(request, response);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HashMap<String, User> connectedUsers = (HashMap<String, User>)getServletContext().getAttribute("connectedUsers");

        if (username == null || password == null || connectedUsers == null ||
                !connectedUsers.containsKey(username) || !connectedUsers.get(username).getPassword().equals(password)) {
            session.setError(HttpServletResponse.SC_UNAUTHORIZED, "Incorrect username/password combination.", "login.jsp");
            return;
        }

        session.connectUser(username);
        session.forward("account.jsp");
    }
}
