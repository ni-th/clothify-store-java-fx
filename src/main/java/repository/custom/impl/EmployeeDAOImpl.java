package repository.custom.impl;

import model.dto.EmployeeDTO;
import model.entity.EmployeeEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.EmployeeDAO;
import util.CRUDUtil;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean add(EmployeeEntity entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(EmployeeEntity entity) {
        return false;
    }

    @Override
    public boolean deleteById(String s) {
        return false;
    }

    @Override
    public EmployeeEntity searchById(String s) throws SQLException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        EmployeeEntity employee = session.get(EmployeeEntity.class,s);
        session.getTransaction().commit();
        return employee;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("FROM EmployeeEntity", EmployeeEntity.class).getResultList();
    }
}
