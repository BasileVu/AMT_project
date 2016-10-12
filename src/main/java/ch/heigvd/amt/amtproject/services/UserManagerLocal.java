package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.exceptions.CreationFailedException;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Local
public interface UserManagerLocal {
    User createUser(String username, String password);
    User get(String username);
    void connectCurrentUser(HttpServletRequest request, String username);
    List<User> getAllUsers();
    boolean userExists(String username);
}
