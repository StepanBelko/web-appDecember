package by.itstep.stpnbelko.homework.dao.impl;

import by.itstep.stpnbelko.homework.dao.AbstractDAO;
import by.itstep.stpnbelko.homework.model.Office;
import by.itstep.stpnbelko.homework.model.User;
import by.itstep.stpnbelko.homework.util.DBUtil;

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
        return null;
    }

    @Override
    public Set<Office> getAll() {
        String sql = "SELECT * FROM users.offices ORDER BY id";
        Set officesList = new LinkedHashSet<User>();

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

}
