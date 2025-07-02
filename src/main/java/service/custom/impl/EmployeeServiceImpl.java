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
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.EMPLOYEE);

    @Override
    public EmployeeDTO loginUser(String username, String password) throws SQLException {

        EmployeeEntity employeeEntity = employeeDAO.searchById(username);
        EmployeeDTO employeeDTO = new ModelMapper().map(employeeEntity, EmployeeDTO.class);
        if (employeeDTO.getPassword().equals(password)){
            return employeeDTO;
        }
        return null;


    }

    @Override
    public List<EmployeeDTO> getAll() {
        List<EmployeeEntity> all = employeeDAO.getAll();
        List<EmployeeDTO> list = new ArrayList<>();
        all.forEach(employeeEntity -> {
            list.add(new ModelMapper().map(employeeEntity, EmployeeDTO.class));
        });
        return  list;
    }

    @Override
    public Boolean add(EmployeeDTO employee) {
        EmployeeEntity mapped = new ModelMapper().map(employee, EmployeeEntity.class);
        return employeeDAO.add(mapped);
    }
}
