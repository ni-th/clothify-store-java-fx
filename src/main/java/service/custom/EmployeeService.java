package service.custom;

import model.Employee;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends SuperService {
    Boolean addEmployee(Employee employee);
    Boolean updateEmployee(Employee employee);
    Boolean deleteEmployee(Employee employee);
    Employee searchById(String id);
    List<Employee> getAll();

    Employee loginUser(String username, String password) throws SQLException;
}
