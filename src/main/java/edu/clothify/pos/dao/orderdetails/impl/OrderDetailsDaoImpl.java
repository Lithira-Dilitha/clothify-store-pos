package edu.clothify.pos.dao.orderdetails.impl;

import edu.clothify.pos.dao.orderdetails.OrderDetailsDao;
import edu.clothify.pos.dto.Orders;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;

public class OrderDetailsDaoImpl implements OrderDetailsDao {

    @Override
    public boolean placeOrder(Orders orders) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(orders);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
