package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.exceptions.CreationFailedException;
import ch.heigvd.amt.amtproject.exceptions.InvalidCredentialsException;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import javax.servlet.http.HttpServletResponse;

@Local
public interface UserManagerLocal {
    void connectUser(HttpServletResponse response, String username, String password) throws InvalidCredentialsException;

    User createUser(String username, String password, String parameter) throws CreationFailedException;
}
