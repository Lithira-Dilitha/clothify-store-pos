package edu.clothify.pos.bo.custom;

import edu.clothify.pos.bo.SuperBo;
import edu.clothify.pos.dto.Customer;

import java.util.List;

public interface CustomerBo extends SuperBo {
    boolean save(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(String customerId);
}
