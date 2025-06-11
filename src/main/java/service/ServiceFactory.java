package service;

import service.custom.EmployeeService;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.ProductServiceImpl;
import service.custom.impl.UserServiceImpl;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;
    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance==null?instance = new ServiceFactory():instance;
    }

    public <T extends SuperService> T getServiceType(ServiceType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeServiceImpl();
            case PRODUCT:return  (T) new ProductServiceImpl();
            case USER:return  (T) new UserServiceImpl();
        }
        return null;
    }
}
