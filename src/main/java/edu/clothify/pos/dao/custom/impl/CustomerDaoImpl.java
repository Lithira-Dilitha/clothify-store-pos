package edu.clothify.pos.dao.custom.impl;

import edu.clothify.pos.dao.custom.CustomerDao;
import edu.clothify.pos.dto.Customer;
import edu.clothify.pos.entity.CustomerEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    @Override
    public List<Customer> getAllCustomers(){
        Session session = HibernateUtil.getSession();
        List<CustomerEntity> allcustomers = session.createQuery("from CustomerEntity", CustomerEntity.class).getResultList();
        List<Customer> customersList = new ArrayList<>();
        allcustomers.forEach(customerEntity ->{
            customersList.add(new ModelMapper().map(customerEntity, Customer.class));
        });

        return customersList;
    }
}
