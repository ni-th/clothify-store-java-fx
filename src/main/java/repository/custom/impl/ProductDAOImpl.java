package repository.custom.impl;

import model.dto.ProductDTO;
import model.entity.ProductEntity;
import repository.custom.ProductDAO;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean add(ProductEntity entity) {
        return false;
    }

    @Override
    public boolean update(ProductEntity entity) {
        return false;
    }

    @Override
    public boolean deleteById(String s) {
        return false;
    }

    @Override
    public ProductEntity searchById(String s) {
        return null;
    }

    @Override
    public List<ProductEntity> getAll() {
        return List.of();
    }
}
