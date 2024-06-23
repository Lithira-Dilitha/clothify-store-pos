package edu.clothify.pos.dao.supplier;

import edu.clothify.pos.dao.CrudDao;
import edu.clothify.pos.dto.Supplier;
import edu.clothify.pos.entity.SupplierEntity;

import java.util.List;

public interface SupplierDao extends CrudDao<SupplierEntity> {
    List<Supplier> getAllSupplierByIsActiveTrue();
    Supplier getSupplierById(String supplierId);
}
