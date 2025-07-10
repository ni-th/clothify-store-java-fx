package repository.custom.impl;

import model.entity.EmployeeEntity;
import org.hibernate.Session;
import repository.custom.UserDAO;
import util.CRUDUtil;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(EmployeeEntity entity) {
        return false;
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
    public List<EmployeeEntity> getAll() {
        return List.of();
    }

    @Override
    public EmployeeEntity searchByUserName(String username) throws SQLException {
        return null;
    }
}
