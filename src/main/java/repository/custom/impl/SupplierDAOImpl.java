package repository.custom.impl;

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
}
