package repository.custom;

import model.dto.ProductDTO;
import model.entity.ProductEntity;
import repository.CrudRepository;

public interface ProductDAO extends CrudRepository<ProductEntity, String> {
}
