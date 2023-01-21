package by.itstep.stpnbelko.homework.dao.impl;

import by.itstep.stpnbelko.homework.dao.AbstractDAO;
import by.itstep.stpnbelko.homework.model.Role;
import by.itstep.stpnbelko.homework.model.User;
import by.itstep.stpnbelko.homework.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class RolesDAO extends AbstractDAO<Role> {

    @Override
    public boolean insert(Role o) {
        return false;
    }

    @Override
    public boolean update(Role o) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Role role) {
        return false;
    }

    @Override
    public Role getById(int id) {
        String sql = "SELECT * FROM roles WHERE roles.id = ?";
        Role role = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                role = new Role();
                role.setId(Integer.parseInt(resultSet.getString("id")));
                role.setName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("descr"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        return role;
    }

    @Override
    public Set getAll() {
        String sql = "SELECT * FROM users.roles ORDER BY id";
        Set rolesList = new LinkedHashSet<Role>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("descr"));
                rolesList.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return rolesList;
    }

    public Set<Role> getUserRoles(User user) {
        String sql = "SELECT * FROM roles WHERE roles.id IN (SELECT role_id FROM users_roles WHERE user_id = ?)";
        Set<Role> usersRoles = new LinkedHashSet<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("descr"));
                usersRoles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return usersRoles;
    }

    public boolean setUserRoles(User user, Set<Role> roleSet) {
        System.out.println("RoleSet size : " + roleSet.size());
//        String deleteSql = "DELETE FROM users.users_roles WHERE (user_id = ?)";
        String sql = "INSERT INTO `users`.`users_roles` (`user_id`, `role_id`) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             /*PreparedStatement deletePreparedStatement = connection.prepareStatement(deleteSql)*/) {

//            deletePreparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(1, user.getId());

//            deletePreparedStatement.execute();
            if (roleSet != null) {
                for (Role role : roleSet) {
                    preparedStatement.setInt(2, role.getId());
                    preparedStatement.execute();
                }
                System.out.println("successfully added " + roleSet.size() + "roles to " + user.getName());
            } else {
                System.out.println("successfully clear all user " + user.getName() + " roles");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean updateUserRoles(User user, Set<Role> roleSet) {
        String deleteSql = "DELETE FROM users.users_roles WHERE (user_id = ?)";
        String sql = "INSERT INTO `users`.`users_roles` (`user_id`, `role_id`) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             PreparedStatement deletePreparedStatement = connection.prepareStatement(deleteSql)) {

            deletePreparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(1, user.getId());

            deletePreparedStatement.execute();
            if (roleSet != null) {
                for (Role role : roleSet) {
                    preparedStatement.setInt(2, role.getId());
                    preparedStatement.execute();
                }
                System.out.println("successfully updated " + roleSet.size() + "roles to " + user.getName());
            } else {
                System.out.println("successfully clear all user " + user.getName() + " roles");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        System.out.println(new RolesDAO().getAll());
    }
}
