package ch.heigvd.amt.amtproject.web.filters;

import ch.heigvd.amt.amtproject.util.URIs;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter related to caching. Allow the cache to be disable on some pages.
 *
 * @author Benjamin Schubert and Basile Vu
 */
@WebFilter(filterName = "CachingFilter")
public class CachingFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse)resp;
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate"); // HTTP 1.0
        response.setHeader("Pragma", "no-cache"); // HTTP 1.1
        response.setDateHeader("Expires", 0); // proxies
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
