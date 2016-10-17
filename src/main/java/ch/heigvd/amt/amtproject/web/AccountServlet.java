package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.services.SessionLocal;
import ch.heigvd.amt.amtproject.services.UserDAOLocal;
import ch.heigvd.amt.amtproject.util.Errors;
import ch.heigvd.amt.amtproject.util.FieldLength;

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
    UserDAOLocal userDAO;

    public static final String USED_JSP = "account.jsp";
    public static final String QUOTE_TOO_LONG = "Your quote is too long (max " + FieldLength.QUOTE_MAX_LENGTH + " chars)";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quote = "";
        try {
            quote = userDAO.get(session.getCurrentUsername(request)).getQuote();
        } catch (RuntimeException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, USED_JSP);
        }
        request.setAttribute("quote", quote);
        request.getRequestDispatcher(JSP_FOLDER + USED_JSP).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quote = request.getParameter("quote");

        if (quote == null) {
            quote = "";
        }

        if (quote.length() > FieldLength.QUOTE_MAX_LENGTH) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_BAD_REQUEST, QUOTE_TOO_LONG , USED_JSP);
            return;
        }

        User u;
        try {
            u = userDAO.get(session.getCurrentUsername(request));
            u.setQuote(quote);
            userDAO.update(u);
        } catch (RuntimeException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Errors.CLIENT_500, USED_JSP);
            return;
        }

        request.setAttribute("quote", quote);
        request.getRequestDispatcher(JSP_FOLDER + USED_JSP).forward(request, response);
    }
}
