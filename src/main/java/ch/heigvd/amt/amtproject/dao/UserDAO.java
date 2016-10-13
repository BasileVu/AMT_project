package ch.heigvd.amt.amtproject.dao;

import ch.heigvd.amt.amtproject.model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAO {
    @Resource(lookup = "java:/jdbc/amtproject")
    DataSource source;

    public boolean create(User u) {
        try (Connection connection = source.getConnection()) {
            connection.prepareStatement(
                    String.format("INSERT INTO user VALUES(\"%s\", \"%s\")", u.getUsername(), u.getPassword())
            ).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public User get(String username) {
        User res = null;
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username=\"" + username + "\"");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String password = rs.getString("password");
                res = new User(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<User> getAll() {
        List<User> res = new ArrayList<>();
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                res.add(new User(username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public boolean delete(String username) {
        try (Connection connection = source.getConnection()) {
            connection.prepareStatement("DELETE FROM user WHERE username=\"" + username +"\"").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
