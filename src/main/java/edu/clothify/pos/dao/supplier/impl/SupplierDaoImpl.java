package edu.clothify.pos.dao.supplier.impl;

import edu.clothify.pos.dao.supplier.SupplierDao;
import edu.clothify.pos.dto.Supplier;
import edu.clothify.pos.entity.SupplierEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {

    @Override
    public boolean save(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(String id, SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("update SupplierEntity set name = :name,company = :company," +
                " email = :email,isActive = :isActive where supplierId =:supplierId");
        query.setParameter("name",entity.getName());
        query.setParameter("company",entity.getCompany());
        query.setParameter("email",entity.getEmail());
        query.setParameter("isActive",entity.getIsActive());
        query.setParameter("employeeId",id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("update SupplierEntity set isActive = :isActive " +
                "where supplierId = :supplierId");
        query.setParameter("isActive",false);
        query.setParameter("supplierId",id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Supplier> getAllSupplierByIsActiveTrue() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<SupplierEntity> allSuppliers = session.createQuery("from SupplierEntity where isActive =true", SupplierEntity.class)
                .getResultList();
        List<Supplier> supplierList = new ArrayList<>();
        allSuppliers.forEach(supplierEntity ->{
            supplierList.add(new ModelMapper().map(supplierEntity,Supplier.class));
        });
        session.getTransaction().commit();
        session.close();
        return supplierList;
    }

    @Override
    public Supplier getSupplierById(String supplierId) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SupplierEntity supplier = session.createQuery("from SupplierEntity where id = :supplierId"
                        , SupplierEntity.class)
                .setParameter("supplierId", supplierId)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return new ModelMapper().map(supplier, Supplier.class);
    }
}