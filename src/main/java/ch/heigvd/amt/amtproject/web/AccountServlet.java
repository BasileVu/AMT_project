package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.dao.UserDAO;
import ch.heigvd.amt.amtproject.services.Session;
import ch.heigvd.amt.amtproject.services.SessionLocal;
import ch.heigvd.amt.amtproject.util.Errors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

@WebServlet(name = "AccountServlet", urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet {
    @EJB
    SessionLocal session;

    @EJB
    UserDAO userDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quote = "";
        try {
            quote = userDAO.get(session.getCurrentUsername(request)).getQuote();
        } catch (RuntimeException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, "account.jsp");
        }
        request.setAttribute("quote", quote);
        request.getRequestDispatcher(JSP_FOLDER + "account.jsp").forward(request, response);
    }
}
