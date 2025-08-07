package repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T,ID> extends SuperRepository{
    boolean add(T entity);
    boolean update(T entity);
    boolean deleteById(ID id) throws SQLException;
    T searchById(ID id) throws SQLException;
    List<T> getAll();
    Integer getLastID();
}
