package repository;

import repository.custom.impl.UserDAOImpl;
import repository.custom.impl.EmployeeDAOImpl;
import repository.custom.impl.ProductDAOImpl;
import util.RepositoryType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance==null?instance=new DaoFactory():instance;
    }
    public <T extends SuperRepository>T getRepositoryType(RepositoryType type){
        switch (type){
            case EMPLOYEE: return (T) new EmployeeDAOImpl();
            case PRODUCT: return (T) new ProductDAOImpl();
            case CATEGORY: return (T) new UserDAOImpl();
        }
        return null;
    }
}
