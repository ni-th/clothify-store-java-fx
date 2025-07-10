package service.custom;

import model.dto.ProductDTO;
import model.entity.ProductEntity;
import service.SuperService;

import java.sql.SQLException;

public interface ProductService extends SuperService {
    Boolean add(ProductDTO productDTO);
    ProductDTO searchById(Integer id) throws SQLException;
}
