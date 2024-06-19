package edu.clothify.pos.bo;

import edu.clothify.pos.bo.custom.impl.CustomerBoImpl;
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
        }
        return null;
    }
}