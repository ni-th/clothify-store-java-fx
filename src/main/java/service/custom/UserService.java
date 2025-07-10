package service.custom;

import model.dto.EmployeeDTO;
import service.SuperService;

import java.sql.SQLException;

public interface UserService extends SuperService {
    EmployeeDTO loginUser(Integer id, String password) throws SQLException;
}
