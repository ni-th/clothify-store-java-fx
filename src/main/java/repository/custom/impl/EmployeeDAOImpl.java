package repository.custom.impl;

import model.dto.EmployeeDTO;
import model.entity.EmployeeEntity;
import repository.custom.EmployeeDAO;
import util.CRUDUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean add(EmployeeEntity entity) {
        return false;
    }

    @Override
    public boolean update(EmployeeEntity entity) {
        return false;
    }

    @Override
    public boolean deleteById(String s) {
        return false;
    }

    @Override
    public EmployeeEntity searchById(String s) throws SQLException {
        ResultSet resultSet = CRUDUtil.execute("SELECT * FROM user WHERE email = ?", s);
        if (resultSet.next())
            return new EmployeeEntity(
                    resultSet.getString("user_id"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("user_type")
            );
        return null;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return List.of();
    }
}
