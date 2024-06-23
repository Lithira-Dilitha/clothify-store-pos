package edu.clothify.pos.dao;

import edu.clothify.pos.dao.custom.impl.CustomerDaoImpl;
import edu.clothify.pos.dao.employee.EmployeeDao;
import edu.clothify.pos.dao.employee.impl.EmployeeDaoImpl;
import edu.clothify.pos.dao.item.impl.ItemDaoImpl;
import edu.clothify.pos.utill.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return instance !=null?instance : (instance=new DaoFactory());
    }
    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case CUSTOMER:return (T) new CustomerDaoImpl();
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case ITEM:return (T) new ItemDaoImpl();
        }
        return null;
    }
}
