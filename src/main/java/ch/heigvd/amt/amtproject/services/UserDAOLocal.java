package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserDAOLocal {
    void create(String username, String password) throws RuntimeException;
    User get(String username) throws RuntimeException;
    List<User> getAll() throws RuntimeException;
    void update(User u) throws RuntimeException;
    void delete(String username) throws RuntimeException;
}
