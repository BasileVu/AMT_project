package ch.heigvd.amt.amtproject.util;

public class Authentication {
    public static boolean passwordValid(String inputPassword, String storedPassword) {
        if (inputPassword == null || storedPassword == null) {
            return false;
        }
        // FIXME
        return inputPassword.equals(storedPassword);
    }
}
