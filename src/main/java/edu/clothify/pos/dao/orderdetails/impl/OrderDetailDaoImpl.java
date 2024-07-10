package edu.clothify.pos.dao.orderdetails.impl;

import edu.clothify.pos.dao.orderdetails.OrderDetailsDao;
import edu.clothify.pos.entity.OrderDetailsEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailsDao {

    @Override
    public boolean addOrderDetails(OrderDetailsEntity orderDetails) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(orderDetails);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean addOrderDetails(List<OrderDetailsEntity> OrderDetailsList) {
        boolean isAdd=false;
        for (OrderDetailsEntity orderDetails : OrderDetailsList) {
             isAdd = addOrderDetails(orderDetails);
        }
        if(isAdd){
            return true;
        }
        return false;
    }
}
