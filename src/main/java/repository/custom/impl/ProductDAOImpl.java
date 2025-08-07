package repository.custom.impl;

import model.entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteById(Integer s) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        ProductEntity productEntity = searchById(s);
        if (productEntity != null){
            session.delete(productEntity);
            transaction.commit();
            session.close();
            return true;
        }else{
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public ProductEntity searchById(Integer id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        ProductEntity productEntity = session.get(ProductEntity.class, id);
        session.getTransaction().commit();
        return productEntity;
    }

    @Override
    public List<ProductEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query<ProductEntity> fromProduct = session.createQuery("FROM ProductEntity", ProductEntity.class);
        fromProduct.getResultList();
        return fromProduct.getResultList();
    }

    @Override
    public Integer getLastID() {
        return 0;
    }

    @Override
    public Boolean updateQty(Integer id, Integer qty) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String qry = "UPDATE ProductEntity p SET p.qty = :qty WHERE p.id = :id";
        Query<?> query = session.createQuery(qry);
        query.setParameter("qty", qty);
        query.setParameter("id", id);
        Integer i = query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }
}
