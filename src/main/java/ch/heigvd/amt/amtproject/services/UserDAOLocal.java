package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.exception.SQLExceptionWrapper;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import java.sql.SQLException;
import java.util.List;

@Local
public interface UserDAOLocal {
    void create(String username, String password, String quote) throws SQLExceptionWrapper;
    User get(String username) throws SQLExceptionWrapper;
    List<User> getAll() throws SQLExceptionWrapper;
    void update(User u) throws SQLExceptionWrapper;
    void delete(String username) throws SQLExceptionWrapper;
}
