package repository.custom.impl;

import model.entity.CartItemEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.CartItemDAO;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {
    @Override
    public CartItemEntity searchByUserName(String username) throws SQLException {
        return null;
    }

    @Override
    public boolean add(CartItemEntity entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(CartItemEntity entity) {
        return false;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }

    @Override
    public CartItemEntity searchById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<CartItemEntity> getAll() {
        return List.of();
    }
}
