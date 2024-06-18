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
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerDao.getCustomerById(customerId);
    }

}
