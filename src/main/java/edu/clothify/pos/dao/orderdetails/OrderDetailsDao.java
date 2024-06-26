package edu.clothify.pos.dao.orderdetails;

import edu.clothify.pos.dao.SuperDao;
import edu.clothify.pos.dto.Orders;

public interface OrderDetailsDao extends SuperDao {
    boolean placeOrder(Orders orders);
}
