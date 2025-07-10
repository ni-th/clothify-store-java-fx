package repository.custom;

import model.entity.EmployeeEntity;
import repository.CrudRepository;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudRepository<EmployeeEntity,Integer> {
    EmployeeEntity searchByUserName(String username) throws SQLException;
}
