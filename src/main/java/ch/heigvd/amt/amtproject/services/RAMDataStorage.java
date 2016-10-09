package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Stateless;
import java.util.HashMap;

@Stateless
public class RAMDataStorage implements RAMDataStorageLocal {

    private final HashMap<String, User> users = new HashMap<String, User>();

    public User getUser(String username) {
        return users.get(username);
    }

    public void putUser(User u) {
        users.put(u.getUsername(), u);
    }

    public boolean containsUser(String username) {
        return users.containsKey(username);
    }
}
