package edu.clothify.pos.bo.orderdetails.impl;

import edu.clothify.pos.bo.orderdetails.OrderDetailsBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.orderdetails.OrderDetailsDao;
import edu.clothify.pos.dto.Orders;
import edu.clothify.pos.utill.DaoType;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;

public class OrderDetailsBoImpl implements OrderDetailsBo {
    OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDao(DaoType.Orders);
    @Override
    public boolean PlaceOrder(Orders orders) {
        return orderDetailsDao.placeOrder(orders);
    }

    @Override
    public String generateOrderID() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        String lastOrderId = session.createQuery("SELECT o.OrderId FORM OrdersEntity o ORDER BY o.OrderId DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();
        String newOrderId;
        if(lastOrderId == null){
            newOrderId = "O001";
        }else{

        }
        return "";
    }
}
