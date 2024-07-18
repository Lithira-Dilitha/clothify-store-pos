package edu.clothify.pos.bo.custom;

import edu.clothify.pos.bo.SuperBo;
import edu.clothify.pos.dto.Customer;

import java.util.List;

public interface CustomerBo extends SuperBo {
    boolean save(Customer customer);
    List<Customer> getAllCustomerByIsActiveTrue();
    Customer getCustomerById(String customerId);
    boolean updateCustomer(String CustomerId ,Customer customer);
    boolean deleteCustomer(String customerId);
    String generateCustomerId();
    Long getAllCustomerCount();
}
