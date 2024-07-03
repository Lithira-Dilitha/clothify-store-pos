package edu.clothify.pos.bo.orders.impl;

import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.item.ItemBo;
import edu.clothify.pos.bo.orderdetails.OrderDetailsBo;
import edu.clothify.pos.bo.orders.OrdersBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.orders.OrdersDao;
import edu.clothify.pos.dto.Orders;
import edu.clothify.pos.entity.ItemEntity;
import edu.clothify.pos.entity.OrdersEntity;
import edu.clothify.pos.utill.BoType;
import edu.clothify.pos.utill.DaoType;
import org.modelmapper.ModelMapper;

public class OrdersBoImpl implements OrdersBo {
    OrdersDao ordersDao = DaoFactory.getInstance().getDao(DaoType.Orders);
    OrderDetailsBo orderDetailsBo = BoFactory.getInstance().getBo(BoType.ORDER_DETAILS);
    ItemBo itemBo = BoFactory.getInstance().getBo(BoType.ITEM);
    ModelMapper mapper = new ModelMapper();
    @Override
    public String getOrderId() {
        return ordersDao.generateOrderId();
    }

    @Override
    public boolean placeOrder(Orders order) {
        boolean isAdd = ordersDao.placeOrder(mapper.map(order, OrdersEntity.class));
        if(isAdd){
            boolean isOrderDetailsAdd = orderDetailsBo.addOrderDetails(order.getOrderDetails());
            if(isOrderDetailsAdd){
               return itemBo.updateStock(order.getOrderDetails());
            }
        }
        return false;
    }

}
