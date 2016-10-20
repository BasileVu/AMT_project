package ch.heigvd.amt.amtproject.web.filters;

import ch.heigvd.amt.amtproject.util.Session;
import ch.heigvd.amt.amtproject.util.URIs;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter related to authentication. Allow protected pages access based on whether the user is connected.
 *
 * @author Benjamin Schubert and Basile Vu
 */
@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String uri = request.getRequestURI().substring(request.getContextPath().length());

        boolean connected = Session.isCurrentUserConnected(request);

        if (connected && (uri.equals(URIs.LOGIN) || uri.equals(URIs.REGISTER))) {
            response.sendRedirect(request.getContextPath() + URIs.ACCOUNT);
        }

        else if (!connected && (uri.equals(URIs.ACCOUNT) || uri.equals(URIs.USERS))) {
            response.sendRedirect(request.getContextPath() + URIs.LOGIN);
        }

        else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
