package ch.heigvd.amt.amtproject.web;

import ch.heigvd.amt.amtproject.services.SessionLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    @EJB
    SessionLocal session;

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        boolean connected = session.isCurrentUserConnected(request);

        if (connected && (path.equals("/login") || path.equals("/register"))) {
            response.sendRedirect(request.getContextPath() + "/account");
        }

        else if (!connected && path.equals("/account")) {
            response.sendRedirect(request.getContextPath() + "/login");
        }

        else {
            chain.doFilter(req, resp);
        }
    }

    public void destroy() {

    }
}
