package service.custom.impl;

import model.dto.ProductDTO;
import model.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDAO;
import service.custom.ProductService;
import util.RepositoryType;
import util.ServiceType;

public class ProductServiceImpl implements ProductService {
    ProductDAO productDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.PRODUCT);

    @Override
    public Boolean add(ProductDTO productDTO) {
        return productDAO.add(new ModelMapper().map(productDTO, ProductEntity.class));
    }

}
