package edu.clothify.pos.bo.orders;

import edu.clothify.pos.bo.SuperBo;
import edu.clothify.pos.dto.Orders;

public interface OrdersBo extends SuperBo {
    String getOrderId();
    boolean placeOrder(Orders order);
}
