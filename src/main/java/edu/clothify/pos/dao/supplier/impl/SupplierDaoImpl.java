package edu.clothify.pos.dao.supplier.impl;

import edu.clothify.pos.dao.supplier.SupplierDao;
import edu.clothify.pos.dto.Employee;
import edu.clothify.pos.dto.Supplier;
import edu.clothify.pos.entity.CustomerEntity;
import edu.clothify.pos.entity.EmployeeEntity;
import edu.clothify.pos.entity.SupplierEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierDaoImpl implements SupplierDao {

    ModelMapper mapper = new ModelMapper();
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
        Query query = session.createQuery("update SupplierEntity set name = :name" +
                ",company = :company" +
                ",email = :email,isActive = :isActive where supplierId =:supplierId");
        query.setParameter("name",entity.getName());
        query.setParameter("company",entity.getCompany());
        query.setParameter("email",entity.getEmail());
        query.setParameter("isActive",entity.getIsActive());
        query.setParameter("supplierId",id);
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
        List<SupplierEntity> supplierEntities = session.createQuery("from SupplierEntity where isActive =true"
                , SupplierEntity.class).getResultList();
        List<Supplier> supplierList = new ArrayList<>();
        supplierEntities.forEach(entity ->{
            supplierList.add(mapper.map(entity,Supplier.class));
        });
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
        return mapper.map(supplier,Supplier.class);
    }

    @Override
    public String generateSupplierId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String lastOrderId = session.createQuery("SELECT s.supplierId FROM SupplierEntity s ORDER BY s.supplierId DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        String newOrderId = "";
        if(lastOrderId == null){
            newOrderId="S0001";
        }else {
            Pattern pattern = Pattern.compile("S(\\d+)");
            Matcher matcher = pattern.matcher(lastOrderId);
            if(matcher.find()){
                int number = Integer.parseInt(matcher.group(1));
                number++;
                newOrderId =String.format("S%04d",number);
            }
        }
        session.getTransaction().commit();
        session.close();
        return newOrderId;
    }

    @Override
    public Long getAllSupplierCount() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Object employeeCount = session.createQuery("SELECT COUNT(*) FROM SupplierEntity")
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return (Long) employeeCount;
    }
}
