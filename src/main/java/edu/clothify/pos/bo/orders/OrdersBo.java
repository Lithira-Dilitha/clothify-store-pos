package edu.clothify.pos.bo.orders;

import edu.clothify.pos.bo.SuperBo;
import edu.clothify.pos.dto.CartTable;
import edu.clothify.pos.dto.Orders;

import java.util.List;
import java.util.Map;

public interface OrdersBo extends SuperBo {
    String getOrderId();
    boolean placeOrder(Orders order);
    void generateBill(List<CartTable> list, Map<String,Object> parameters);
}
