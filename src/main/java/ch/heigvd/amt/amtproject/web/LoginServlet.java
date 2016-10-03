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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HashMap<String, User> connectedUsers = (HashMap<String, User>)getServletContext().getAttribute("connectedUsers");

        if (username == null || password == null || connectedUsers == null ||
                !connectedUsers.containsKey(username) || !connectedUsers.get(username).getPassword().equals(password)) {
            ErrorHandler.setError(request, response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Incorrect username/password combination.", "WEB-INF/pages/login.jsp");
            return;
        }

        request.getSession().setAttribute("username", username);
        request.getRequestDispatcher("WEB-INF/pages/account.jsp").forward(request, response);
    }
}
