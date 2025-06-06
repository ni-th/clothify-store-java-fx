package repository.custom;

import model.entity.EmployeeEntity;
import repository.CrudRepository;

public interface EmployeeDAO extends CrudRepository<EmployeeEntity,String> {
}
