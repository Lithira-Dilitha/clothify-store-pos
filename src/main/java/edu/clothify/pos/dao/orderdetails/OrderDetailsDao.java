package edu.clothify.pos.dao.orderdetails;

import edu.clothify.pos.dao.SuperDao;
import edu.clothify.pos.entity.OrderDetailsEntity;

import java.util.List;

public interface OrderDetailsDao extends SuperDao {
    boolean addOrderDetails(OrderDetailsEntity orderDetails);
    boolean addOrderDetails(List<OrderDetailsEntity> OrderDetailsList);
    String generateOrderDetailsId();
}
