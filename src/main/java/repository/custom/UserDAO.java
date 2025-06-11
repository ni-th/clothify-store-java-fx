package repository.custom;

import model.entity.CategoryEntity;
import repository.CrudRepository;

public interface UserDAO extends CrudRepository<CategoryEntity,String> {
}
