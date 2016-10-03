package ch.heigvd.amt.amtproject.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
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

        // static files
        if (path.startsWith("/static")) {
            chain.doFilter(req, resp);
            return;
        }

        // if any pages other than /logout, redirect to user page
        if (request.getSession().getAttribute("username") != null && !path.equals("/logout")) {
            request.getRequestDispatcher("WEB-INF/pages/connected.jsp").forward(request, response);
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void destroy() {

    }
}
