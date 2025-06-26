package service.custom;

import model.dto.EmployeeDTO;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends SuperService {
//    Boolean addEmployee(EmployeeDTO employeeDTO);
//    Boolean updateEmployee(EmployeeDTO employeeDTO);
//    Boolean deleteEmployee(EmployeeDTO employeeDTO);
//    EmployeeDTO searchById(String id);
//    List<EmployeeDTO> getAll();

    EmployeeDTO loginUser(String username, String password) throws SQLException;

    List<EmployeeDTO> getAll();
}
