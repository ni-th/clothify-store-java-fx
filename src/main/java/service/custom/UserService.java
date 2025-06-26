package service.custom;

import model.dto.EmployeeDTO;
import service.SuperService;

import java.sql.SQLException;

public interface UserService extends SuperService {
    EmployeeDTO loginUser(String username, String password) throws SQLException;
}
