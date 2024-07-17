package edu.clothify.pos.dao.orderdetails.impl;

import edu.clothify.pos.dao.orderdetails.OrderDetailsDao;
import edu.clothify.pos.entity.OrderDetailsEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Override
    public String generateOrderDetailsId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String lastOrderId = session.createQuery("SELECT i.id FROM OrderDetailsEntity i ORDER BY i.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        String newOrderId = "";
        if(lastOrderId == null){
            newOrderId="D0001";
        }else {
            Pattern pattern = Pattern.compile("D(\\d+)");
            Matcher matcher = pattern.matcher(lastOrderId);
            if(matcher.find()){
                int number = Integer.parseInt(matcher.group(1));
                number++;
                newOrderId =String.format("D%04d",number);
            }
        }
        session.getTransaction().commit();
        session.close();
        return newOrderId;
    }
}
