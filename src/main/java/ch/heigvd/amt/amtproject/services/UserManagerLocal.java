package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.exceptions.CreationFailedException;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;

@Local
public interface UserManagerLocal {

    User createUser(String username, String password, String parameter) throws CreationFailedException;

    User get(String username);

    void connectUser(HttpServletRequest request, String username, String password) throws CredentialException;
}
