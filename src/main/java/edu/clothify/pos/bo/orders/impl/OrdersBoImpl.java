package edu.clothify.pos.bo.orders.impl;

import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.item.ItemBo;
import edu.clothify.pos.bo.orderdetails.OrderDetailsBo;
import edu.clothify.pos.bo.orders.OrdersBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.orders.OrdersDao;
import edu.clothify.pos.dto.CartTable;
import edu.clothify.pos.dto.OrderDetails;
import edu.clothify.pos.dto.Orders;
import edu.clothify.pos.entity.OrdersEntity;
import edu.clothify.pos.utill.BoType;
import edu.clothify.pos.utill.DaoType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;

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
        System.out.println(order);
        boolean isAdd = ordersDao.placeOrder(mapper.map(order, OrdersEntity.class));
        if(isAdd){
            boolean isOrderDetailsAdd = orderDetailsBo.addOrderDetails(order.getOrderDetailsList());
            if(isOrderDetailsAdd){
                return itemBo.updateStock(order.getOrderDetailsList());
            }
        }
        return false;
    }

    @Override
    public JasperPrint generateBill(List<CartTable> list, Map<String,Object> parameters) {
        try {

            String path = "src/main/resources/reports/invoice.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(path);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            var jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
            return jasperPrint;
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getAllOrdersCount() {
        return ordersDao.getAllOrdersCount();
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersDao.getAllOrders();
    }


}
