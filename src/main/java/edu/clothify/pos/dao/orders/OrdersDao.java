package edu.clothify.pos.dao.orders;

import edu.clothify.pos.dao.SuperDao;
import edu.clothify.pos.dto.Orders;
import edu.clothify.pos.entity.OrderDetailsEntity;
import edu.clothify.pos.entity.OrdersEntity;

import java.util.List;

public interface OrdersDao extends SuperDao {
    String generateOrderId();
    boolean placeOrder(OrdersEntity order);
    Long getAllOrdersCount();
    List<Orders> getAllOrders();
}
