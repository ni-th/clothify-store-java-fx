package service.custom;

import model.dto.EmployeeDTO;
import model.entity.EmployeeEntity;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends SuperService {
    EmployeeDTO loginUser(String id, String password) throws SQLException;
    List<EmployeeDTO> getAll();
    Boolean add(EmployeeDTO employee);
    Boolean update(EmployeeDTO employeeDTO);
    EmployeeEntity searchByUserName(String username) throws SQLException;
    Boolean creatAdmin();
    void generateReport();
    Integer passwordValidator(String password);
    String passwordEncrypter(String password);
    Boolean checkPassword(String password,String hash);
    Boolean emailValidator(String email);
}
