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
    public boolean deleteById(Integer s) {
        return false;
    }

    @Override
    public EmployeeEntity searchById(Integer s) throws SQLException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        EmployeeEntity employee = session.get(EmployeeEntity.class,s);
        session.getTransaction().commit();
        return employee;
    }

    @Override
    public EmployeeEntity searchByUserName(String username) throws SQLException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        EmployeeEntity employee = session.createQuery("FROM EmployeeEntity WHERE email = :email", EmployeeEntity.class)
                .setParameter("email", username)
                .uniqueResult();
        session.getTransaction().commit();
        return employee;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("FROM EmployeeEntity", EmployeeEntity.class).getResultList();
    }

    @Override
    public Integer getLastID() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        EmployeeEntity employee = session.createQuery("SELECT * FROM product ORDER BY id DESC LIMIT 1", EmployeeEntity.class)
                .uniqueResult();
        return employee.getId();
    }
    @Override
    public Boolean creatAdmin(EmployeeEntity employeeEntity) throws SQLException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        EmployeeEntity employeeEntity1 = searchById(1);
        if (employeeEntity1 == null){
            session.save(employeeEntity);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }
}
