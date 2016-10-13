package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.dao.UserDAO;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Stateless
public class Session implements SessionLocal {
    public static final String USERNAME_KEY = "username";

    @Override
    public boolean isCurrentUserConnected(HttpServletRequest request) {
        return request.getSession().getAttribute(USERNAME_KEY) != null;
    }

    @Override
    public void connectCurrentUser(HttpServletRequest request, String username) {
        request.getSession().setAttribute(USERNAME_KEY, username);
    }

    @Override
    public void disconnectCurrentUser(HttpServletRequest request) {
        request.getSession().removeAttribute(USERNAME_KEY);
    }
}
