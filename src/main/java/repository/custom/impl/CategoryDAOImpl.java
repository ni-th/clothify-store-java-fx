package repository.custom.impl;

import model.entity.CategoryEntity;
import repository.custom.CategoryDAO;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public boolean add(CategoryEntity entity) {
        return false;
    }

    @Override
    public boolean update(CategoryEntity entity) {
        return false;
    }

    @Override
    public boolean deleteById(String s) {
        return false;
    }

    @Override
    public CategoryEntity searchById(String s) {
        return null;
    }

    @Override
    public List<CategoryEntity> getAll() {
        return List.of();
    }
}
