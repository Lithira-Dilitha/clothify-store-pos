package edu.clothify.pos.dao.custom.impl;

import edu.clothify.pos.dao.custom.CustomerDao;
import edu.clothify.pos.dto.Customer;
import edu.clothify.pos.entity.CustomerEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
        List<CustomerEntity> allcustomers = session.createQuery("from CustomerEntity", CustomerEntity.class)
                .getResultList();
        List<Customer> customersList = new ArrayList<>();
        allcustomers.forEach(customerEntity ->{
            customersList.add(new ModelMapper().map(customerEntity, Customer.class));
        });

        return customersList;
    }
    @Override
    public Customer getCustomerById(String customerId){
        Session session = HibernateUtil.getSession();
        CustomerEntity customer = session.createQuery("from CustomerEntity where id = :customerId"
                        , CustomerEntity.class)
                .setParameter("customerId", customerId)
                .uniqueResult();
       return new ModelMapper().map(customer, Customer.class);
    }

    @Override
    public boolean updateCustomer(String customerId,CustomerEntity customer){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("update CustomerEntity set name = :name" +
                ",address = :address" +
                ",email = :email where customerId =:customerId");
//        query.setParameter("customerId",customer.getCustomerId());
        query.setParameter("name",customer.getName());
        query.setParameter("address",customer.getAddress());
        query.setParameter("email",customer.getEmail());
        query.setParameter("customerId",customerId);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
