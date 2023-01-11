package by.itstep.stpnbelko.homework.dao.impl;

import by.itstep.stpnbelko.homework.dao.AbstractDAO;
import by.itstep.stpnbelko.homework.model.User;
import by.itstep.stpnbelko.homework.util.DBUtil;
import by.itstep.stpnbelko.homework.util.IOUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsersDAO extends AbstractDAO<User> {

    @Override
    public boolean insert(User user) {
        System.out.println("UsersDAO insert method");
        Connection connection = DBUtil.getConnection();
        String sql = "INSERT INTO users.users (name, email, password) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("Successfully added " + user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.release(connection, null, preparedStatement, null);
        }

        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public User getById(User user) {
        return null;
    }

    @Override
    public Set<User> getAll() {

        String sql = "SELECT * FROM users.users ORDER BY created_ts";
        Set userList = new HashSet<User>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new UsersDAO().getByEmail(resultSet.getString("email"));
                userList.add(user);
            }
//            System.out.println(userList.size());

            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User getByEmail(String email) {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM users.users WHERE email = '" + email + "'";
        Statement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                user = new User();
                user.setEmail(email);
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.set_active(resultSet.getBoolean("is_active"));
                user.setCreated_ts(resultSet.getString("created_ts"));
                user.setUpdated_ts(resultSet.getString("updated_ts"));
//                System.out.println("User is found " + user.toString());
            } else {
                System.out.println("User is not exist " + email);
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.release(connection, statement, null, resultSet);
        }

        return user;
    }

    public boolean updateByEmail(String email) {
        String sql = "UPDATE users.users\n SET is_active = 1, updated_ts = CURRENT_TIMESTAMP WHERE email = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            int result = preparedStatement.executeUpdate();

            if (result == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean isActive(String email) {
        String sql = "SELECT is_active FROM users.users WHERE email = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean changePassWord(String email, String newPass) {
        String sql = "UPDATE users.users\n SET password = " + newPass
                + ", updated_ts = CURRENT_TIMESTAMP WHERE email = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    public static void main(String[] args) {
        Set<User> userSet = new UsersDAO().getAll();

        for (User user : userSet) {
            System.out.println(user);
        }
    }
}
