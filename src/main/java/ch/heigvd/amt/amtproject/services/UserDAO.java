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
    public void create(String username, String password, String quote) throws SQLException {
        String query =
                "INSERT INTO user (" +
                    "username, " +
                    "password, " +
                    "quote" +
                ") VALUES (?, ?, ?)";

        try (
                Connection connection = source.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, quote);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(String username) throws SQLException {
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
                    long id = rs.getLong("id");
                    String password = rs.getString("password");
                    String quote = rs.getString("quote");
                    res = new User(id, username, password, quote);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public List<User> getAll() throws SQLException {
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
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String quote = rs.getString("quote");
                res.add(new User(id, username, password, quote));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public void update(User u) throws SQLException {
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
    public void delete(String username) throws SQLException {
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
