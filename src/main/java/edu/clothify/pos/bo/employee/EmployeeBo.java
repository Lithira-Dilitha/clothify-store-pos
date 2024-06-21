package edu.clothify.pos.bo.employee;

import edu.clothify.pos.bo.SuperBo;
import edu.clothify.pos.dto.Customer;
import edu.clothify.pos.dto.Employee;

import java.util.List;

public interface EmployeeBo extends SuperBo {
    boolean save(Employee employee);
    List<Employee> getAllEmployeeByIsActiveTrue();
    Employee getEmployeeById(String employeeId);
    boolean updateEmployee(String employeeId ,Employee employee);
    boolean deleteEmployee(String employeeId);
}
