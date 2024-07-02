package edu.clothify.pos.dao.orderdetails;

import edu.clothify.pos.dao.SuperDao;
import edu.clothify.pos.entity.OrderDetailsEntity;

public interface OrderDetailsDao extends SuperDao {
    boolean addOrderDetails(OrderDetailsEntity orderDetails);
}
