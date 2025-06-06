package service.custom.impl;

import repository.DaoFactory;
import repository.custom.ProductDAO;
import service.custom.ProductService;
import util.RepositoryType;
import util.ServiceType;

public class ProductServiceImpl implements ProductService {
    ProductDAO productDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.PRODUCT);
}
