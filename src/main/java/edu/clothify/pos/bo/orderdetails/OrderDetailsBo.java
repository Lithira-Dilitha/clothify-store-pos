package edu.clothify.pos.bo.orderdetails;

import edu.clothify.pos.dto.Orders;

public interface OrderDetailsBo {
    boolean PlaceOrder(Orders orders);
    String generateOrderID();
}
