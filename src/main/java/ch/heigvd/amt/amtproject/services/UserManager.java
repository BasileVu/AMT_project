package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.exceptions.CreationFailedException;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Stateless
public class UserManager implements UserManagerLocal {

    public static final String USERNAME_KEY = "username";

    @EJB
    RAMDataStorageLocal storage;

    public static boolean isCurrentUserConnected(HttpServletRequest request) {
        return request.getSession().getAttribute(USERNAME_KEY) != null;
    }

    @Override
    public User createUser(String username, String password) {
        User u = new User(username, password);
        storage.putUser(u);

        return u;
    }

    @Override
    public void connectCurrentUser(HttpServletRequest request, String username) {
        request.getSession().setAttribute(USERNAME_KEY, username);
    }

    @Override
    public User get(String username) {
        return storage.getUser(username);
    }

    @Override
    public List<User> getAllUsers() {
        return storage.getAllUsers();
    }

    @Override
    public boolean userExists(String username) {
        return storage.containsUser(username);
    }
}
