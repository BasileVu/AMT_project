package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.util.Keys;
import ch.heigvd.amt.amtproject.util.Session;
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
import java.sql.SQLException;

import static ch.heigvd.amt.amtproject.util.Paths.JSP_FOLDER;

@WebServlet(name = "AccountServlet", urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet {
    @EJB
    UserDAOLocal userDAO;

    public static final String USED_JSP = "account.jsp";
    public static final String QUOTE_TOO_LONG_ERROR = "Your quote is too long (max " + FieldLength.QUOTE_MAX_LENGTH + " chars)";
    public static final String ACCOUNT_UPDATED_INFO = "Your account has been successfully updated.";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quote = "";
        try {
            quote = userDAO.get(Session.getCurrentUsername(request)).getQuote();
        } catch (SQLException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Errors.CLIENT_500, USED_JSP);
        }
        request.setAttribute(Keys.QUOTE, quote);
        request.getRequestDispatcher(JSP_FOLDER + USED_JSP).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quote = request.getParameter(Keys.QUOTE);

        if (quote == null) {
            quote = "";
        }

        if (quote.length() > FieldLength.QUOTE_MAX_LENGTH) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_BAD_REQUEST, QUOTE_TOO_LONG_ERROR, USED_JSP);
            return;
        }

        User u;
        try {
            u = userDAO.get(Session.getCurrentUsername(request));
            u.setQuote(quote);
            userDAO.update(u);
        } catch (SQLException e) {
            Errors.setErrorAndForward(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Errors.CLIENT_500, USED_JSP);
            return;
        }

        request.setAttribute(Keys.QUOTE, quote);
        request.setAttribute(Keys.INFO, ACCOUNT_UPDATED_INFO);
        request.getRequestDispatcher(JSP_FOLDER + USED_JSP).forward(request, response);
    }
}
