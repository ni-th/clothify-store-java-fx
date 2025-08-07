package service;

import service.custom.EmployeeService;
import service.custom.impl.*;
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
            case SUPPLIER:return  (T) new SupplierServiceImpl();
            case CARTITEM:return  (T) new CartItemServiceImpl();
            case EMAIL:return  (T) new CartItemServiceImpl();
        }
        return null;
    }
}
