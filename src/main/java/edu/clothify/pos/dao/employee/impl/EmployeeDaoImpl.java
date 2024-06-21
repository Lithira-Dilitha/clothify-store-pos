package edu.clothify.pos.dao.employee.impl;

import edu.clothify.pos.dao.employee.EmployeeDao;
import edu.clothify.pos.dto.Employee;
import edu.clothify.pos.entity.EmployeeEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> getAllEmployeeByIsActiveTrue() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<EmployeeEntity> allEmployees = session.createQuery("from EmployeeEntity where isActive =true", EmployeeEntity.class)
                .getResultList();
        List<Employee> employeeList = new ArrayList<>();
        allEmployees.forEach(employeeEntity ->{
            employeeList.add(new ModelMapper().map(employeeEntity,Employee.class));
        });
        session.getTransaction().commit();
        session.close();
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        EmployeeEntity employee = session.createQuery("from EmployeeEntity where id = :employeeId"
                        , EmployeeEntity.class)
                .setParameter("employeeId", employeeId)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return new ModelMapper().map(employee, Employee.class);
    }



    @Override
    public boolean save(EmployeeEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(String id, EmployeeEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("update EmployeeEntity set name = :name" +
                ",address = :address" +
                ",email = :email,isActive = :isActive where employeeId =:employeeId");
//        query.setParameter("customerId",customer.getCustomerId());
        query.setParameter("name",entity.getName());
        query.setParameter("address",entity.getAddress());
        query.setParameter("email",entity.getEmail());
        query.setParameter("isActive",entity.getIsActive());
        query.setParameter("employeeId",id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("update EmployeeEntity set isActive = :isActive " +
                "where employeeId = :employeeId");
        query.setParameter("isActive",false);
        query.setParameter("employeeId",id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
