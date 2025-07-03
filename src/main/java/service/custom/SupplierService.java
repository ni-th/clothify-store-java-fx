package service.custom;

import model.dto.SupplierDTO;
import service.SuperService;

public interface SupplierService extends SuperService {
    Boolean add(SupplierDTO supplierDTO);
}
