package edu.clothify.pos.bo.employee.impl;

import edu.clothify.pos.bo.employee.EmployeeBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dto.Customer;
import edu.clothify.pos.dto.Employee;

import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {

    @Override
    public boolean save(Employee employee) {
        return false;
    }

    @Override
    public List<Customer> getAllEmployeeByIsActiveTrue() {
        return List.of();
    }

    @Override
    public Customer getEmployeeById(String employeeId) {
        return null;
    }

    @Override
    public boolean updateEmployee(String employeeId, Employee employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(String employeeId) {
        return false;
    }
}
