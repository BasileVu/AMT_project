package ch.heigvd.amt.amtproject.dao;

import ch.heigvd.amt.amtproject.model.User;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserDAO {
    @Resource(lookup = "java:/jdbc/amtproject")
    DataSource source;

    public void create(User u) throws RuntimeException {
        try (
                Connection connection = source.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO user " +
                        "VALUES(\"" + u.getUsername() + "\", \"" + u.getPassword() + "\")"
                )
        ) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User get(String username) throws RuntimeException {
        User res = null;
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * " +
                        "FROM user " +
                        "WHERE username=\"" + username + "\""
                );
                ResultSet rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                String password = rs.getString("password");
                res = new User(username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    public List<User> getAll() throws RuntimeException {
        List<User> res = new ArrayList<>();
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * " +
                        "FROM user"
                );
                ResultSet rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                res.add(new User(username, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    public void delete(String username) throws RuntimeException {
        try (
                Connection connection = source.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "DELETE FROM user " +
                        "WHERE username=\"" + username +"\""
                )
        ) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
