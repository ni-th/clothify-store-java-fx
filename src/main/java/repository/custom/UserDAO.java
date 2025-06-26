package repository.custom;

import model.entity.CategoryEntity;
import model.entity.EmployeeEntity;
import repository.CrudRepository;

public interface UserDAO extends CrudRepository<EmployeeEntity,String> {
}
