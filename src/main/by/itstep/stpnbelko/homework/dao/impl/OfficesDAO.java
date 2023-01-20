package by.itstep.stpnbelko.homework.dao.impl;

import by.itstep.stpnbelko.homework.dao.AbstractDAO;
import by.itstep.stpnbelko.homework.model.Office;
import by.itstep.stpnbelko.homework.model.User;
import by.itstep.stpnbelko.homework.util.DBUtil;
import jdk.jshell.spi.SPIResolutionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class OfficesDAO extends AbstractDAO<Office> {

    @Override
    public boolean insert(Office office) {
        return false;
    }

    @Override
    public boolean update(Office office) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Office getById(int id) {

        String sql = "SELECT * FROM users.offices WHERE id = ?";
        Office office = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, String.valueOf(id));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                office = new Office();
                office.setId(Integer.parseInt(resultSet.getString("id")));
                office.setName(resultSet.getString("name"));
                office.setLocation(resultSet.getString("location"));
                office.setPhone(resultSet.getString("phone"));
                office.setFax(resultSet.getString("fax"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return office;
    }

    @Override
    public Set<Office> getAll() {
        String sql = "SELECT * FROM users.offices ORDER BY id";
        Set<Office> officesList = new LinkedHashSet<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Office office = new Office();
                office.setId(Integer.parseInt(resultSet.getString("id")));
                office.setName(resultSet.getString("name"));
                office.setLocation(resultSet.getString("location"));
                office.setPhone(resultSet.getString("phone"));
                office.setFax(resultSet.getString("fax"));
                officesList.add(office);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return officesList;
    }

    public static void main(String[] args) {
        Set<Office> offices = new OfficesDAO().getAll();
        System.out.println(offices);
    }

}
