package edu.clothify.pos.dao;

import edu.clothify.pos.dao.custom.impl.CustomerDaoImpl;
import edu.clothify.pos.dao.employee.impl.EmployeeDaoImpl;
import edu.clothify.pos.dao.item.impl.ItemDaoImpl;
import edu.clothify.pos.dao.orderdetails.impl.OrderDetailDaoImpl;
import edu.clothify.pos.dao.orders.impl.OrdersDaoImpl;
import edu.clothify.pos.dao.supplier.impl.SupplierDaoImpl;
import edu.clothify.pos.dao.user.impl.UserDaoImpl;
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
            case SUPPLIER:return (T) new SupplierDaoImpl();
            case USER: return (T) new UserDaoImpl();
            case Orders:return (T) new OrdersDaoImpl();
            case ORDER_DETAILS: return (T) new OrderDetailDaoImpl();
        }
        return null;
    }
}
