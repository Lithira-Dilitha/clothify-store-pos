package edu.clothify.pos.dao.employee;

import edu.clothify.pos.dao.CrudDao;
import edu.clothify.pos.dto.Employee;
import edu.clothify.pos.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDao extends CrudDao<EmployeeEntity> {
    List<Employee> getAllEmployeeByIsActiveTrue();
    Employee getEmployeeById(String employeeId);
}
