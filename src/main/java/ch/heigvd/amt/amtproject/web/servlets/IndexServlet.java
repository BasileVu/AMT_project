package ch.heigvd.amt.amtproject.web.servlets;

import ch.heigvd.amt.amtproject.util.Paths;
import ch.heigvd.amt.amtproject.util.URIs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet handling the requests related to the webapp main page.
 *
 * @author Benjamin Schubert and Basile Vu
 */
@WebServlet(name = "IndexServlet", urlPatterns = {URIs.INDEX})
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Paths.JSP_FOLDER + "index.jsp").forward(request, response);
    }
}
