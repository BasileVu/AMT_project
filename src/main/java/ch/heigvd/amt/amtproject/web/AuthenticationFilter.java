package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.services.UserManager;
import ch.heigvd.amt.amtproject.services.UserManagerLocal;
import ch.heigvd.amt.amtproject.util.ErrorHandler;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        boolean connected = UserManager.isCurrentUserConnected(request);

        if (connected && (path.equals("/login") || path.equals("/register"))) {
            request.getRequestDispatcher("account.jsp").forward(request, response);
        }

        else if (!connected && path.equals("/account")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        else {
            chain.doFilter(req, resp);
        }
    }

    public void destroy() {

    }
}
