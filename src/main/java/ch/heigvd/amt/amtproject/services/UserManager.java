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

    public User createUser(String username, String password, String passwordConfirmation) throws CreationFailedException {
        if (username.equals("") || password.equals("") || passwordConfirmation.equals("")) {
            throw new CreationFailedException("All the fields must be filled.");
        }

        if (storage.containsUser(username)) {
            throw new CreationFailedException("User already exists.");
        }

        if (!password.equals(passwordConfirmation)) {
            throw new CreationFailedException("Passwords don't match.");
        }

        User u = new User(username, password);
        storage.putUser(u);

        return u;
    }

    public void connectCurrentUser(HttpServletRequest request, String username) {
        request.getSession().setAttribute(USERNAME_KEY, username);
    }

    public User get(String username) {
        return storage.getUser(username);
    }

    public static boolean isCurrentUserConnected(HttpServletRequest request) {
        return request.getSession().getAttribute(USERNAME_KEY) != null;
    }

    public List<User> getAllUsers() {
        return storage.getAllUsers();
    }
}
