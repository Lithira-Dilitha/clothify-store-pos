package edu.clothify.pos.bo.custom.impl;

import edu.clothify.pos.bo.custom.CustomerBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.custom.CustomerDao;
import edu.clothify.pos.dto.Customer;
import edu.clothify.pos.entity.CustomerEntity;
import edu.clothify.pos.utill.DaoType;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    @Override
    public boolean save(Customer customer) {
       return customerDao.save(new ModelMapper().map(customer, CustomerEntity.class));
    }

    @Override
    public List<Customer> getAllCustomerByIsActiveTrue() {
        return customerDao.getAllCustomerByIsActiveTrue();
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerDao.getCustomerById(customerId);
    }

    @Override
    public boolean updateCustomer(String customerId ,Customer customer){
        return customerDao.update(customerId,new ModelMapper().map(customer, CustomerEntity.class));
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        return customerDao.delete(customerId);
    }

    @Override
    public String generateCustomerId() {
        return customerDao.generateCustomerId();
    }

}
