package service.custom.impl;

import model.dto.EmployeeDTO;
import model.dto.SupplierDTO;
import model.entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDAO;
import repository.custom.SupplierDAO;
import service.custom.SupplierService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    SupplierDAO supplierDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.SUPPLIER);
    @Override
    public Boolean add(SupplierDTO supplierDTO) {
        return supplierDAO.add(new ModelMapper().map(supplierDTO, SupplierEntity.class));
    }

    @Override
    public List<SupplierDTO> getAll() {
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        supplierDAO.getAll().forEach(supplier ->{
            supplierDTOList.add(new ModelMapper().map(supplier, SupplierDTO.class));
        });
        return supplierDTOList;
    }

    @Override
    public SupplierEntity searchByUserName(String username) throws SQLException {
        return supplierDAO.searchByUserName(username);
    }

    @Override
    public void generateReport() {

    }

    @Override
    public Boolean deleteByUsername(String email) {
        try {
            return supplierDAO.deleteByUserName(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
