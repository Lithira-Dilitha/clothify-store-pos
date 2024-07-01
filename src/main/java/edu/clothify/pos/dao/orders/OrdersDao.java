package edu.clothify.pos.dao.orders;

import edu.clothify.pos.dao.SuperDao;

public interface OrdersDao extends SuperDao {
    String generateOrderId();
}
