package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.exceptions.CreationFailedException;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

@Local
public interface UserManagerLocal {

    User createUser(String username, String password, String parameter) throws CreationFailedException;

    User get(String username);

    void connectCurrentUser(HttpServletRequest request, String username);
}
