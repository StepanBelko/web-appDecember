package by.itstep.stpnbelko.homework.dao.impl;

import by.itstep.stpnbelko.homework.dao.AbstractDAO;
import by.itstep.stpnbelko.homework.model.Office;
import by.itstep.stpnbelko.homework.model.Role;
import by.itstep.stpnbelko.homework.model.User;
import by.itstep.stpnbelko.homework.util.DBUtil;

import java.sql.*;
import java.util.*;

public class UsersDAO extends AbstractDAO<User> {

//    private static String COLUMNS = " u.id, u.name, u.email, u.password, u.is_active, u.created_ts, u.updated_ts, o.id, o.location, o.phone, o.fax FROM users.users u JOIN  offices o ON u.office_id = o.id and u.email = ?"

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
    public boolean delete(int id) {
        String sql = "DELETE FROM `users`.`users` WHERE (`id` = ?)";


        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 1)
                return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public User getById(int id) {

        String sql = "SELECT u.id as User_id, u.name as User_name, u.email, u.password, u.is_active, u.created_ts, u.updated_ts, " +
                "o.id as Office_id, o.name as Office_name, o.location, o.phone, o.fax " +
                "FROM users.users u " +
                "JOIN  offices o " +
                "ON u.office_id = o.id and u.id = ?";


//        String getRolesSql = "SELECT * FROM roles WHERE roles.id IN (SELECT role_id FROM users_roles WHERE user_id = ?)";
        User user = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                Office office = new Office();
                user.setId(id);
                user.setName(resultSet.getString("User_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setOffice_id(resultSet.getInt("Office_id"));
                user.setIs_active(resultSet.getBoolean("is_active"));
                user.setCreated_ts(resultSet.getTimestamp("created_ts"));
                user.setUpdated_ts(resultSet.getTimestamp("updated_ts"));

                office.setId(resultSet.getInt("Office_id"));
                office.setName(resultSet.getString("Office_name"));
                office.setLocation(resultSet.getString("location"));
                office.setPhone(resultSet.getString("phone"));
                office.setFax(resultSet.getString("fax"));

                user.setOffice(office);

//                Вариант 1 заполнения ролей
//                в этом случае мы создаём новый connection, новый RolesDAO()...
//                итд со всеми вытекающими последствиями:

                user.setRole(new RolesDAO().getUserRoles(user));

//                Вариант 2. В этом же connection. new RolesDAO() не нужен:
//                preparedStatementForGetRole.setInt(1, id);
//                ResultSet rolesResultSet = preparedStatementForGetRole.executeQuery();
//                Set<Role> usersRoles = new LinkedHashSet<>();
//
//                while (rolesResultSet.next()) {
//                    Role role = new Role();
//                    role.setId(rolesResultSet.getInt("id"));
//                    role.setName(rolesResultSet.getString("name"));
//                    role.setDescription(rolesResultSet.getString("descr"));
//                    usersRoles.add(role);
//                }
//                user.setRole(usersRoles);

            } else {
                System.out.println("User does not exist. id =  " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public Set<User> getAll() {

        String sql = "SELECT u.id as User_id, u.name as User_name, u.email, u.password, u.is_active, u.created_ts, u.updated_ts, o.id as Office_id, o.name as Office_name, o.location, o.phone, o.fax \n" +
                "FROM users.users u " +
                "JOIN  offices o " +
                "ON u.office_id = o.id";
        Set userList = new LinkedHashSet<User>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                Office office = new Office();
                user.setId(resultSet.getInt("User_id"));
                user.setName(resultSet.getString("User_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setOffice_id(resultSet.getInt("office_id"));
                user.setIs_active(resultSet.getBoolean("is_active"));
                user.setCreated_ts(resultSet.getTimestamp("created_ts"));
                user.setUpdated_ts(resultSet.getTimestamp("updated_ts"));

                user.setRole(new RolesDAO().getUserRoles(user));

                office.setId(resultSet.getInt("Office_id"));
                office.setName(resultSet.getString("Office_name"));
                office.setLocation(resultSet.getString("location"));
                office.setPhone(resultSet.getString("phone"));
                office.setFax(resultSet.getString("fax"));

                user.setOffice(office);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return userList;
    }

    public User getByEmail(String email) {
        String sql = "SELECT u.id as User_id, u.name as User_name, u.email, u.password, u.is_active, u.created_ts, u.updated_ts, o.id as Office_id, o.name as Office_name, o.location, o.phone, o.fax \n" +
                "FROM users.users u \n" +
                "JOIN  offices o \n" +
                "ON u.office_id = o.id and u.email = ?";
//        String getRolesSql = "SELECT * FROM roles WHERE roles.id IN (SELECT role_id FROM users_roles WHERE user_id = ?)";
        User user = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                Office office = new Office();
                user.setId(resultSet.getInt("User_id"));
                user.setName(resultSet.getString("User_name"));
                user.setEmail(email);
                user.setPassword(resultSet.getString("password"));
                user.setOffice_id(resultSet.getInt("office_id"));
                user.setIs_active(resultSet.getBoolean("is_active"));
                user.setCreated_ts(resultSet.getTimestamp("created_ts"));
                user.setUpdated_ts(resultSet.getTimestamp("updated_ts"));

                user.setRole(new RolesDAO().getUserRoles(user));

                office.setId(resultSet.getInt("Office_id"));
                office.setName(resultSet.getString("Office_name"));
                office.setLocation(resultSet.getString("location"));
                office.setPhone(resultSet.getString("phone"));
                office.setFax(resultSet.getString("fax"));
                user.setOffice(office);

//                Вариант 1 заполнения ролей
//                в этом случае мы создаём новый connection, новый RolesDAO()...
//                итд со всеми вытекающими последствиями:

//                user.setRole(new RolesDAO().getUserRoles(user));

//                Вариант 2. В этом же connection. new RolesDAO() не нужен:
//                preparedStatementForGetRole.setInt(1, user.getId());
//                ResultSet rolesResultSet = preparedStatementForGetRole.executeQuery();
//                Set<Role> usersRoles = new LinkedHashSet<>();
//
//                while (rolesResultSet.next()) {
//                    Role role = new Role();
//                    role.setId(rolesResultSet.getInt("id"));
//                    role.setName(rolesResultSet.getString("name"));
//                    role.setDescription(rolesResultSet.getString("descr"));
//                    usersRoles.add(role);
//                }
//                user.setRole(usersRoles);

            } else {
                System.out.println("User does not exist " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return user;
    }

    public boolean updateByEmail(String email) {
        String sql = "UPDATE users.users SET is_active = 1, updated_ts = CURRENT_TIMESTAMP WHERE email = ?";

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
        System.out.println(new UsersDAO().getAll());
    }
}
