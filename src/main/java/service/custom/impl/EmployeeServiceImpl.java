package service.custom.impl;

import model.dto.EmployeeDTO;
import repository.DaoFactory;
import repository.custom.EmployeeDAO;
import repository.custom.impl.EmployeeDAOImpl;
import service.custom.EmployeeService;
import util.CRUDUtil;
import util.RepositoryType;
import util.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.EMPLOYEE);
//    @Override
//    public Boolean addEmployee(EmployeeDTO employeeDTO) {
//        return null;
//    }
//
//    @Override
//    public Boolean updateEmployee(EmployeeDTO employeeDTO) {
//        return null;
//    }
//
//    @Override
//    public Boolean deleteEmployee(EmployeeDTO employeeDTO) {
//        return null;
//    }
//
//    @Override
//    public EmployeeDTO searchById(String id) {
//        return null;
//    }
//
//    @Override
//    public List<EmployeeDTO> getAll() {
//        return List.of();
//    }

    @Override
    public EmployeeDTO loginUser(String username, String password) throws SQLException {
        ResultSet resultSet = CRUDUtil.execute("SELECT * FROM user WHERE email = ? AND password = ?", username,password);
        if (resultSet.next())
            return new EmployeeDTO(
                    resultSet.getString("user_id"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("user_type")
            );
        return null;
    }
}
