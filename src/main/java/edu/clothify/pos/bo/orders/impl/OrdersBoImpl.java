package edu.clothify.pos.bo.orders.impl;

import edu.clothify.pos.bo.orders.OrdersBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.orders.OrdersDao;
import edu.clothify.pos.utill.DaoType;

public class OrdersBoImpl implements OrdersBo {
    OrdersDao ordersDao = DaoFactory.getInstance().getDao(DaoType.Orders);
    @Override
    public String getOrderId() {
        return ordersDao.generateOrderId();
    }
}
