package edu.clothify.pos.dao.orders;

import edu.clothify.pos.dao.SuperDao;
import edu.clothify.pos.entity.OrdersEntity;

public interface OrdersDao extends SuperDao {
    String generateOrderId();
    boolean placeOrder(OrdersEntity order);
}
