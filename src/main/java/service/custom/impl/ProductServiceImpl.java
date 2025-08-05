package service.custom.impl;

import model.dto.EmployeeDTO;
import model.dto.ProductDTO;
import model.entity.EmployeeEntity;
import model.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDAO;
import service.custom.ProductService;
import util.RepositoryType;
import util.ServiceType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDAO productDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.PRODUCT);

    @Override
    public Boolean add(ProductDTO productDTO) {
        return productDAO.add(new ModelMapper().map(productDTO, ProductEntity.class));
    }

    @Override
    public ProductDTO searchById(Integer id) throws SQLException {
        if (productDAO.searchById(id) == null){
            return null;
        }
        return new ModelMapper().map(productDAO.searchById(id), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductEntity> all = productDAO.getAll();
        List<ProductDTO> list = new ArrayList<>();
        all.forEach(productEntity -> {
            list.add(new ModelMapper().map(productEntity, ProductDTO.class));
        });
        return  list;
    }

    @Override
    public Boolean updateQty(Integer id, Integer qty) throws SQLException {
        ProductEntity productEntity = productDAO.searchById(id);
        Integer qtyInDB = productEntity.getQty();
        return productDAO.updateQty(id,qtyInDB-qty);
    }

}
