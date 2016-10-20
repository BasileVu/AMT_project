package ch.heigvd.amt.amtproject.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Regroups static methods used to manage a user session.
 *
 * @author Benjamin Schubert and Basile Vu
 */
public class Session {
    public static final String USERNAME_KEY = "username";

    public static String getCurrentUsername(HttpServletRequest request) {
        return (String)request.getSession().getAttribute(USERNAME_KEY);
    }

    public static boolean isCurrentUserConnected(HttpServletRequest request) {
        return getCurrentUsername(request) != null;
    }

    public static void connectCurrentUser(HttpServletRequest request, String username) {
        request.getSession().setAttribute(USERNAME_KEY, username);
    }

    public static void disconnectCurrentUser(HttpServletRequest request) {
        request.getSession().removeAttribute(USERNAME_KEY);
    }
}
