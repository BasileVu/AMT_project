package ch.heigvd.amt.amtproject.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

@WebServlet(name = "IndexServlet", urlPatterns = {""})
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JSP_FOLDER + "index.jsp").forward(request, response);
    }
}
