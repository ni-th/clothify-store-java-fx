package service.custom;

import model.dto.ProductDTO;
import model.entity.ProductEntity;
import service.SuperService;

public interface ProductService extends SuperService {
    Boolean add(ProductDTO productDTO);
}
