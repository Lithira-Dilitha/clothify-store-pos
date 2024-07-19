package edu.clothify.pos.dao.orders.impl;

import edu.clothify.pos.dao.orders.OrdersDao;
import edu.clothify.pos.dto.Orders;
import edu.clothify.pos.entity.ItemEntity;
import edu.clothify.pos.entity.OrderDetailsEntity;
import edu.clothify.pos.entity.OrdersEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrdersDaoImpl implements OrdersDao {
    @Override
    public String generateOrderId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String lastOrderId = session.createQuery("SELECT o.orderId FROM OrdersEntity o ORDER BY o.orderId DESC", String.class)
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
        System.out.println("this is Order Entity Object :"+order);
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(order);
        session.getTransaction().commit();
        return true;

    }

    @Override
    public Long getAllOrdersCount() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Object orderCount = session.createQuery("SELECT COUNT(*) FROM OrdersEntity")
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return (Long) orderCount;
    }

    @Override
    public List<Orders> getAllOrders() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<OrdersEntity> ordersEntityList = session.createQuery("from OrdersEntity", OrdersEntity.class)
                .getResultList();
        List<Orders> ordersList = new ArrayList<>();
        ordersEntityList.forEach(orders ->{
            ordersList.add(new ModelMapper().map(orders,Orders.class));
        });
        return ordersList;
    }
}
