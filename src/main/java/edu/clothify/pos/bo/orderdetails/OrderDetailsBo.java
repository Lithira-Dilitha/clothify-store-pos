package edu.clothify.pos.bo.orderdetails;

import edu.clothify.pos.bo.SuperBo;
import edu.clothify.pos.dto.OrderDetails;

import java.util.List;

public interface OrderDetailsBo extends SuperBo {
    boolean addOrderDetails(List<OrderDetails> orderDetailsList);
}
