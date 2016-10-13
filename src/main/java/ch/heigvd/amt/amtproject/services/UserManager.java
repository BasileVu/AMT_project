package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.dao.UserDAO;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Stateless
public class UserManager implements UserManagerLocal {

    public static final String USERNAME_KEY = "username";

    @EJB
    UserDAO userDAO;

    public static boolean isCurrentUserConnected(HttpServletRequest request) {
        return request.getSession().getAttribute(USERNAME_KEY) != null;
    }

    @Override
    public User createUser(String username, String password) {
        User u = new User(username, password);
        userDAO.create(u);
        return u;
    }

    @Override
    public void connectCurrentUser(HttpServletRequest request, String username) {
        request.getSession().setAttribute(USERNAME_KEY, username);
    }

    @Override
    public User get(String username) {
        return userDAO.get(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @Override
    public boolean userExists(String username) {
        return userDAO.get(username) != null;
    }
}
