package ch.heigvd.amt.amtproject.util;

import ch.heigvd.amt.amtproject.model.User;

public class Authentication {
    public static boolean passwordValid(String inputPassword, User user) {
        // FIXME
        return inputPassword != null && user != null && inputPassword.equals(user.getPassword());
    }
}
