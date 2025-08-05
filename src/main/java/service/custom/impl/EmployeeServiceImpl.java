package service.custom.impl;

import model.dto.EmployeeDTO;
import model.entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDAO;
import service.custom.EmployeeService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.EMPLOYEE);

    @Override
    public EmployeeDTO loginUser(String id, String password) throws SQLException {

        EmployeeEntity employeeEntity = employeeDAO.searchByUserName(id);
        if (employeeEntity == null){
            return null;
        }
        if (employeeEntity.getPassword().equals(password)){
            return new ModelMapper().map(employeeEntity, EmployeeDTO.class);
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

    @Override
    public EmployeeEntity searchByUserName(String username) throws SQLException {
        return employeeDAO.searchByUserName(username);
    }

    @Override
    public Boolean creatAdmin() {
        EmployeeEntity employeeEntity = new EmployeeEntity(1, "admin", "admin@gmail.com", "a", "super-admin");
        try {
            return employeeDAO.creatAdmin(employeeEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
