package repository.custom.impl;

import model.dto.ProductDTO;
import model.entity.ProductEntity;
import model.entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.ProductDAO;
import util.HibernateUtil;

import java.sql.SQLException;
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
        return true;
    }

    @Override
    public boolean deleteById(Integer s) {
        return false;
    }

    @Override
    public ProductEntity searchById(Integer s) {
        return null;
    }

    @Override
    public List<ProductEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query<ProductEntity> fromProduct = session.createQuery("FROM ProductEntity", ProductEntity.class);
        fromProduct.getResultList();
        return fromProduct.getResultList();
    }
}
