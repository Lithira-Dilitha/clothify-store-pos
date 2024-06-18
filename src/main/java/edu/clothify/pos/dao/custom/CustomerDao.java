package edu.clothify.pos.dao.custom;

import edu.clothify.pos.dao.CrudDao;
import edu.clothify.pos.dto.Customer;
import edu.clothify.pos.entity.CustomerEntity;

import java.util.List;

public interface CustomerDao extends CrudDao<CustomerEntity> {
    List<Customer> getAllCustomers();
    Customer getCustomerById(String customerId);
    boolean updateCustomer(String customerId,CustomerEntity customer);
}
