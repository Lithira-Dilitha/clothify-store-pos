package edu.clothify.pos.bo.orders.impl;

import edu.clothify.pos.bo.orders.OrdersBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.orders.OrdersDao;
import edu.clothify.pos.dto.Orders;
import edu.clothify.pos.entity.OrdersEntity;
import edu.clothify.pos.utill.DaoType;
import org.modelmapper.ModelMapper;

public class OrdersBoImpl implements OrdersBo {
    OrdersDao ordersDao = DaoFactory.getInstance().getDao(DaoType.Orders);
    ModelMapper mapper = new ModelMapper();
    @Override
    public String getOrderId() {
        return ordersDao.generateOrderId();
    }

    @Override
    public boolean placeOrder(Orders order) {
        return ordersDao.placeOrder(mapper.map(order, OrdersEntity.class));
    }
}
