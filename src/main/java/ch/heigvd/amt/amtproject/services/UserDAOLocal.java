package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import java.sql.SQLException;
import java.util.List;

@Local
public interface UserDAOLocal {
    void create(String username, String password, String quote) throws SQLException;
    User get(String username) throws SQLException;
    List<User> getAll() throws SQLException;
    void update(User u) throws SQLException;
    void delete(String username) throws SQLException;
}
