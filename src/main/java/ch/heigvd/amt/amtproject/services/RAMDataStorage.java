package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
public class RAMDataStorage implements RAMDataStorageLocal {

    private final HashMap<String, User> users = new HashMap<String, User>();

    public User getUser(String username) {
        return users.get(username);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void putUser(User u) {
        users.put(u.getUsername(), u);
    }

    public boolean containsUser(String username) {
        return users.containsKey(username);
    }
}
