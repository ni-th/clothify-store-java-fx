package repository.custom.impl;

import model.entity.CartItemEntity;
import model.entity.EmployeeEntity;
import model.entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.SupplierDAO;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean add(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(SupplierEntity entity) {
        return false;
    }

    @Override
    public boolean deleteById(Integer s) {
        return false;
    }

    @Override
    public SupplierEntity searchById(Integer s) throws SQLException {
        return null;
    }

    @Override
    public List<SupplierEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query<SupplierEntity> fromSupplier = session.createQuery("FROM SupplierEntity", SupplierEntity.class);
        fromSupplier.getResultList();
        return fromSupplier.getResultList();
    }

    @Override
    public Integer getLastID() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        SupplierEntity supplier = session.createNativeQuery("SELECT * FROM supplier ORDER BY id DESC LIMIT 1", SupplierEntity.class)
                .uniqueResult();
        if (supplier != null) {
            return supplier.getId();
        } else {
            return null;
        }
    }

    @Override
    public SupplierEntity searchByUserName(String username) throws SQLException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        SupplierEntity supplier = session.createQuery("FROM SupplierEntity WHERE email = :email", SupplierEntity.class)
                .setParameter("email", username)
                .uniqueResult();
        session.getTransaction().commit();
        return supplier;
    }
}
