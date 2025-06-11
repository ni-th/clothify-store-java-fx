package repository.custom.impl;

import model.entity.CategoryEntity;
import repository.custom.UserDAO;

import java.util.List;

public class UserDAOImpl implements UserDAO {
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
