package edu.clothify.pos.bo;

import edu.clothify.pos.bo.custom.impl.CustomerBoImpl;
import edu.clothify.pos.bo.employee.impl.EmployeeBoImpl;
import edu.clothify.pos.bo.item.impl.ItemBoImpl;
import edu.clothify.pos.bo.suplier.impl.SupplierBoImpl;
import edu.clothify.pos.bo.user.impl.UserBoImpl;
import edu.clothify.pos.utill.BoType;

public class BoFactory {
    private static BoFactory instance;
    private BoFactory(){}
    public static BoFactory getInstance(){
        return instance != null ? instance : (instance=new BoFactory());
    }
    public <T extends SuperBo> T getBo(BoType type){
        switch (type){
            case CUSTOMER:return(T) new CustomerBoImpl();
            case EMPLOYEE:return (T)new EmployeeBoImpl();
            case ITEM:return (T) new ItemBoImpl();
            case SUPPLIER:return (T) new SupplierBoImpl();
            case USER:return (T) new UserBoImpl();
        }
        return null;
    }
}
