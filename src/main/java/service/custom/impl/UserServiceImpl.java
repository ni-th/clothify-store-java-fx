package service.custom.impl;

import model.dto.EmployeeDTO;
import model.entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDAO;
import service.custom.UserService;
import util.RepositoryType;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    EmployeeDAO employeeDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.EMPLOYEE);

    @Override
    public EmployeeDTO loginUser(Integer id, String password) throws SQLException {
        EmployeeEntity employeeEntity = employeeDAO.searchById(id);
        EmployeeDTO employeeDTO = new ModelMapper().map(employeeEntity, EmployeeDTO.class);
        if (employeeDTO.getPassword().equals(password)){
            return employeeDTO;
        }
        return null;
    }
}
