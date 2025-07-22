package service.custom;

import model.dto.CartItemDTO;
import model.dto.ProductDTO;
import service.SuperService;

import java.sql.SQLException;

public interface CartItemService extends SuperService {
    Boolean add(CartItemDTO cartItemDTO);
    CartItemDTO searchById(Integer id) throws SQLException;
    Integer getLastID();
}
