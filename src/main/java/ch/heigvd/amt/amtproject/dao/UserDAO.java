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

    public void create(User u) {
        try (Connection connection = source.getConnection()) {
            connection.prepareStatement(
                    String.format("INSERT INTO user VALUES(\"%s\", \"%s\")", u.getUsername(), u.getPassword())
            ).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User get(String username) {
        User res = null;
        try (Connection connection = source.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username=\"" + username + "\"");
            ResultSet rs =  statement.executeQuery();

            while (rs.next()) {
                String fn = rs.getString("username");
                System.out.println(fn);
                res = new User(fn, fn);
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
            ResultSet rs =  statement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(username);
                res.add(new User(username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }
}
