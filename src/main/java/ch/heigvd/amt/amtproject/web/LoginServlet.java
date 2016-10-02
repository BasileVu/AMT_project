package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") != null) {
            request.getRequestDispatcher("WEB-INF/pages/connected.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        System.out.println("test");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Object o = getServletContext().getAttribute("connectedUsers");

        if (username == null || password == null || o == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Incorrect username/password combination.");
            return;
        }

        HashMap<String, User> connectedUsers = (HashMap<String, User>) o;

        if (!connectedUsers.containsKey(username) || !connectedUsers.get(username).getPassword().equals(password)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Incorrect username/password combination.");
            return;
        }

        request.getSession().setAttribute("username", username);
        request.getRequestDispatcher("WEB-INF/pages/connected.jsp").forward(request, response);
    }
}
