package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.exceptions.CreationFailedException;
import ch.heigvd.amt.amtproject.exceptions.InvalidCredentialsException;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Stateless
public class UserManager implements UserManagerLocal {

    public static final String CONNECTED_KEY = "connected";

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

    public void connectUser(HttpServletResponse response, String username, String password) throws InvalidCredentialsException {
        if (username == null || password == null ||
                !storage.containsUser(username) || !storage.getUser(username).getPassword().equals(password)) {
            throw new InvalidCredentialsException("Incorrect username/password combination.");
        }
    }

    public User get(String username) {
        return storage.getUser(username);
    }

    public static void connectCurrentUser(HttpServletRequest request) {
        request.getSession().setAttribute(CONNECTED_KEY, true);
    }

    public static boolean isCurrentUserConnected(HttpServletRequest request) {
        return request.getSession().getAttribute(CONNECTED_KEY) != null;
    }
}
