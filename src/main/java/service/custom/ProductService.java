package service.custom;

import model.dto.EmployeeDTO;
import model.dto.ProductDTO;
import model.entity.ProductEntity;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface ProductService extends SuperService {
    Boolean add(ProductDTO productDTO);
    ProductDTO searchById(Integer id) throws SQLException;
    List<ProductDTO> getAll();
    Boolean updateQty(Integer id, Integer qty) throws SQLException;
    void generateReport();
}
