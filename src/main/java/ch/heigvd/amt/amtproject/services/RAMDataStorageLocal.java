package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;

@Local
public interface RAMDataStorageLocal {
    boolean containsUser(String username);

    void putUser(User u);

    User getUser(String username);
}
