package repository.custom;

import model.entity.EmployeeEntity;
import model.entity.SupplierEntity;
import repository.CrudRepository;

import java.sql.SQLException;

public interface SupplierDAO extends CrudRepository<SupplierEntity, Integer> {
    SupplierEntity searchByUserName(String username) throws SQLException;
    Boolean deleteByUserName(String email) throws SQLException;
}
