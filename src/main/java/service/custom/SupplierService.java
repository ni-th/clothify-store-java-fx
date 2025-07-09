package service.custom;

import model.dto.SupplierDTO;
import service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {
    Boolean add(SupplierDTO supplierDTO);
    List<SupplierDTO> getAll();
}
