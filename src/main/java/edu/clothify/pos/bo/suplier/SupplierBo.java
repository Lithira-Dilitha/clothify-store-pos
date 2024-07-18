package edu.clothify.pos.bo.suplier;

import edu.clothify.pos.bo.SuperBo;
import edu.clothify.pos.dto.Employee;
import edu.clothify.pos.dto.Supplier;

import java.util.List;

public interface SupplierBo extends SuperBo {
    boolean save(Supplier supplier);
    List<Supplier> getAllSupplierByIsActiveTrue();
    Supplier getSupplierById(String supplierId);
    boolean updateSupplier(String supplierId ,Supplier supplier);
    boolean deleteSupplier(String supplierId);
    String generateSupplierID();
    Long getAllSupplierCount();
}
