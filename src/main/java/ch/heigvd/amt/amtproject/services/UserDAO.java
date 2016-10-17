package ch.heigvd.amt.amtproject.services;

import ch.heigvd.amt.amtproject.model.User;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAO implements UserDAOLocal {
    @Resource(lookup = "java:/jdbc/amtproject")
    DataSource source;

    @Override
    public void create(User u) throws RuntimeException {
        String query =
                "INSERT INTO user " +
                "VALUES(?, ?, ?)";

        try (
                Connection connection = source.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getQuote());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(String username) throws RuntimeException {
        String query =
                "SELECT * " +
                "FROM user " +
                "WHERE username = ?";

        User res = null;
        try (
                Connection connection = source.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setString(1, username);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String password = rs.getString("password");
                    String quote = rs.getString("quote");
                    res = new User(username, password, quote);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public List<User> getAll() throws RuntimeException {
        String query =
                "SELECT * " +
                "FROM user";

        List<User> res = new ArrayList<>();
        try (
                Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String quote = rs.getString("quote");
                res.add(new User(username, password, quote));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public void update(User u) throws RuntimeException {
        String query =
                "UPDATE user " +
                "SET password = ?, quote = ? " +
                "WHERE username = ?";

        try (
                Connection connection = source.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, u.getPassword());
            stmt.setString(2, u.getQuote());
            stmt.setString(3, u.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String username) throws RuntimeException {
        String query =
                "DELETE FROM user " +
                "WHERE username = ?";

        try (
                Connection connection = source.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
