package edu.clothify.pos.dao.orders.impl;

import edu.clothify.pos.dao.orders.OrdersDao;
import edu.clothify.pos.entity.OrdersEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrdersDaoImpl implements OrdersDao {

    @Override
    public String generateOrderId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String lastOrderId = session.createQuery("SELECT o.OrderId FROM OrdersEntity o ORDER BY o.OrderId DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        String newOrderId = "";
        if(lastOrderId == null){
            newOrderId="OR0001";
        }else {
            Pattern pattern = Pattern.compile("OR(\\d+)");
            Matcher matcher = pattern.matcher(lastOrderId);
            if(matcher.find()){
                int number = Integer.parseInt(matcher.group(1));
                number++;
               newOrderId =String.format("OR%04d",number);
            }
        }
        session.getTransaction().commit();
        session.close();
        return newOrderId;
    }

    @Override
    public boolean placeOrder(OrdersEntity order) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(order);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
