package edu.clothify.pos.bo.employee.impl;

import edu.clothify.pos.bo.employee.EmployeeBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.employee.EmployeeDao;
import edu.clothify.pos.dto.Employee;
import edu.clothify.pos.entity.EmployeeEntity;
import edu.clothify.pos.utill.DaoType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {


    ModelMapper modelMapper = new ModelMapper();

    EmployeeDao employeeDao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
    @Override
    public boolean save(Employee employee) {
        return employeeDao.save(modelMapper.map(employee, EmployeeEntity.class));
    }

    @Override
    public List<Employee> getAllEmployeeByIsActiveTrue() {
        return employeeDao.getAllEmployeeByIsActiveTrue();
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public boolean updateEmployee(String employeeId, Employee employee) {
        return employeeDao.update(employeeId,modelMapper.map(employee, EmployeeEntity.class));
    }

    @Override
    public boolean deleteEmployee(String employeeId) {
        return employeeDao.delete(employeeId);
    }
}
