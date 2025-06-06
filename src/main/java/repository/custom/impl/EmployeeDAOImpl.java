package repository.custom.impl;

import model.dto.EmployeeDTO;
import model.entity.EmployeeEntity;
import repository.custom.EmployeeDAO;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean add(EmployeeEntity entity) {
        return false;
    }

    @Override
    public boolean update(EmployeeEntity entity) {
        return false;
    }

    @Override
    public boolean deleteById(String s) {
        return false;
    }

    @Override
    public EmployeeEntity searchById(String s) {
        return null;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return List.of();
    }
}
