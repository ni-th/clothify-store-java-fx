package service.custom;

import model.dto.SupplierDTO;
import model.entity.EmployeeEntity;
import model.entity.SupplierEntity;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface SupplierService extends SuperService {
    Boolean add(SupplierDTO supplierDTO);
    List<SupplierDTO> getAll();
    SupplierEntity searchByUserName(String username) throws SQLException;
    void generateReport();
    Boolean deleteByUsername(String email);
    SupplierEntity searchByID(Integer id);
}
