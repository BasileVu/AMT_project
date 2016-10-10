package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RAMDataStorageLocal {
    boolean containsUser(String username);

    void putUser(User u);

    User getUser(String username);

    List<User> getAllUsers();
}
