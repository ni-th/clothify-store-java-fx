package service.custom.impl;

import model.dto.SupplierDTO;
import model.entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDAO;
import repository.custom.SupplierDAO;
import service.custom.SupplierService;
import util.RepositoryType;

public class SupplierServiceImpl implements SupplierService {
    SupplierDAO supplierDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.SUPPLIER);
    @Override
    public Boolean add(SupplierDTO supplierDTO) {
        return supplierDAO.add(new ModelMapper().map(supplierDTO, SupplierEntity.class));
    }
}
