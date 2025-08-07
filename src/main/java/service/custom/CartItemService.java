package service.custom;

import model.dto.CartItemDTO;
import model.dto.EmployeeDTO;
import model.dto.ProductDTO;
import service.ServiceFactory;
import service.SuperService;
import util.ServiceType;

import java.sql.SQLException;
import java.util.List;

public interface CartItemService extends SuperService {
    Boolean add(CartItemDTO cartItemDTO);
    CartItemDTO searchById(Integer id) throws SQLException;
    Integer getLastID();
    List<CartItemDTO> getAll();
    void generateReport(Integer id);
    Integer generateOrderID();
}
