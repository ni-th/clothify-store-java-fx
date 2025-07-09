package repository.custom.impl;

import model.dto.ProductDTO;
import model.entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.ProductDAO;
import util.HibernateUtil;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean add(ProductEntity entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
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
