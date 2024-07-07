package edu.clothify.pos.bo.suplier.impl;

import edu.clothify.pos.bo.suplier.SupplierBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.supplier.SupplierDao;
import edu.clothify.pos.dto.Supplier;
import edu.clothify.pos.entity.SupplierEntity;
import edu.clothify.pos.utill.DaoType;
import org.modelmapper.ModelMapper;

import java.util.List;

public class SupplierBoImpl implements SupplierBo {
    SupplierDao supplierDao  = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean save(Supplier supplier) {
        return supplierDao.save(modelMapper.map(supplier, SupplierEntity.class));
    }

    @Override
    public List<Supplier> getAllSupplierByIsActiveTrue() {
        return supplierDao.getAllSupplierByIsActiveTrue();
    }

    @Override
    public Supplier getSupplierById(String supplierId) {
        return supplierDao.getSupplierById(supplierId);
    }

    @Override
    public boolean updateSupplier(String supplierId, Supplier supplier) {
        return supplierDao.update(supplierId,modelMapper.map(supplier, SupplierEntity.class));
    }

    @Override
    public boolean deleteSupplier(String supplierId) {
        return supplierDao.delete(supplierId);
    }

    @Override
    public String generateSupplierID() {
        return supplierDao.generateSupplierId();
    }
}
