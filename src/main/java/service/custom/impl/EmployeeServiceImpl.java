package service.custom.impl;

import model.Employee;
import service.custom.EmployeeService;
import util.CRUDUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Boolean addEmployee(Employee employee) {
        return null;
    }

    @Override
    public Boolean updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public Boolean deleteEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee searchById(String id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return List.of();
    }

    @Override
    public Employee loginUser(String username, String password) throws SQLException {
        ResultSet resultSet = CRUDUtil.execute("SELECT * FROM user WHERE email = ? AND password = ?", username,password);
        if (resultSet.next())
            return new Employee(
                    resultSet.getString("user_id"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("user_type")
            );
        return null;
    }
}
