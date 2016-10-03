package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.util.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    private final Session session = new Session();

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        session.setup((HttpServletRequest)req, (HttpServletResponse)resp);
        String path = session.getCurrentPath();

        if (session.userConnected() && (path.equals("/login") || path.equals("/register"))) {
            session.forward("account.jsp");
        }

        else if (!session.userConnected() && path.equals("/account")) {
            session.forward("login.jsp");
        }

        else {
            chain.doFilter(req, resp);
        }
    }

    public void destroy() {

    }
}
