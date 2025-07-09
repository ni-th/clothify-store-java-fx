package service.custom;

import model.dto.ProductDTO;
import service.SuperService;

public interface ProductService extends SuperService {
    Boolean add(ProductDTO productDTO);
}
