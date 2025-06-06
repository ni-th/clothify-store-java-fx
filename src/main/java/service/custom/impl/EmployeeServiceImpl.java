package service.custom.impl;

import model.dto.EmployeeDTO;
import model.entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
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

        EmployeeEntity employeeEntity = employeeDAO.searchById(username);
        EmployeeDTO employeeDTO = new ModelMapper().map(employeeEntity, EmployeeDTO.class);
        System.out.println(employeeEntity);
        if (employeeDTO.getPassword().equals(password)){
            return employeeDTO;
        }
        return null;


    }
}
