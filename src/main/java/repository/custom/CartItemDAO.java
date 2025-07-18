package repository.custom;

import model.entity.CartItemEntity;
import repository.CrudRepository;

import java.sql.SQLException;

public interface CartItemDAO extends CrudRepository<CartItemEntity,Integer> {
    CartItemEntity searchByUserName(String username) throws SQLException;
}
