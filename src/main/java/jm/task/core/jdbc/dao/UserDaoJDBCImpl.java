package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.internal.CoreLogging.logger;


public class UserDaoJDBCImpl implements UserDao {
    public final Connection con;

    public UserDaoJDBCImpl() {
        this.con = Util.getConnection();
    }
    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL, " +
                "last_name VARCHAR(45) NOT NULL, " +
                "age TINYINT NOT NULL)";

        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            logger("Users table created");
        } catch (SQLException e) {
            logger(e.getMessage());
        }
    }
    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(sql);
            logger("Users table deleted");
        } catch (SQLException e) {
            logger(e.getMessage());
        }
    }
    @Override
    public void saveUser(String name, String lastname, byte age) {
        String sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setByte(3, age);
            statement.executeUpdate();
            logger("User with name " + name + " saved");
        } catch (SQLException e) {
            logger(e.getMessage());
        }
    }
    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                logger("User with id " + id + " deleted");
            } else {
                logger("User with id " + id + " not deleted");
            }
        } catch (SQLException e) {
            logger(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {

        if (con == null) {
            logger("Connection is null");
            return new ArrayList<>(); // возвращаем пустой список вместо null
        }

        List<User> users = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT FROM users")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            logger("We have " + users.size() + " users");
        } catch (SQLException e) {
            logger(e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users"; // Очищает таблицу, но не удаляет её
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            logger("Users table cleaned");
        } catch (SQLException e) {
            logger(e.getMessage());
        }
        }
    }

