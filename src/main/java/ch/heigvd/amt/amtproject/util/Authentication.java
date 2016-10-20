package ch.heigvd.amt.amtproject.util;

import ch.heigvd.amt.amtproject.model.User;

/**
 * Regroups utility methods used to manage authentication.
 *
 * @author Benjamin Schubert and Basile Vu
 */
public class Authentication {
    /**
     * Checks if a password is valid.
     * @param inputPassword The user input password.
     * @param user The current user.
     * @return Whether the password given is valid.
     */
    public static boolean passwordValid(String inputPassword, User user) {
        // FIXME hash password and compare with user's password
        return inputPassword != null && user != null && inputPassword.equals(user.getPassword());
    }
}
