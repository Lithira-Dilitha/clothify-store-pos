package edu.clothify.pos.bo.orderdetails.impl;

import edu.clothify.pos.bo.orderdetails.OrderDetailsBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.orderdetails.OrderDetailsDao;
import edu.clothify.pos.dto.OrderDetails;
import edu.clothify.pos.entity.OrderDetailsEntity;
import edu.clothify.pos.utill.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBoImpl implements OrderDetailsBo {
    ModelMapper mapper = new ModelMapper();
    OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAILS);
    @Override
    public boolean addOrderDetails(List<OrderDetails> orderDetailsList) {
        List<OrderDetailsEntity> orderDetailsEntityList=new ArrayList<>();
        for (OrderDetails orderDetails : orderDetailsList){
            orderDetailsEntityList.add(mapper.map(orderDetails, OrderDetailsEntity.class));
        }
       return orderDetailsDao.addOrderDetails(orderDetailsEntityList);
    }
}
