package repository.custom;

import model.entity.ProductEntity;
import repository.CrudRepository;

public interface ProductDAO extends CrudRepository<ProductEntity, Integer> {
    Boolean updateQty(Integer id, Integer qty);
}
