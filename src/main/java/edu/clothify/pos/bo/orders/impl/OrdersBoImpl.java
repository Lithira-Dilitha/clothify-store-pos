package edu.clothify.pos.bo.orders.impl;

import edu.clothify.pos.bo.BoFactory;
import edu.clothify.pos.bo.item.ItemBo;
import edu.clothify.pos.bo.orderdetails.OrderDetailsBo;
import edu.clothify.pos.bo.orders.OrdersBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.orders.OrdersDao;
import edu.clothify.pos.dto.CartTable;
import edu.clothify.pos.dto.Orders;
import edu.clothify.pos.entity.OrdersEntity;
import edu.clothify.pos.utill.BoType;
import edu.clothify.pos.utill.DaoType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.modelmapper.ModelMapper;

import java.io.File;
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
        boolean isAdd = ordersDao.placeOrder(mapper.map(order, OrdersEntity.class));
        if(isAdd){
            boolean isOrderDetailsAdd = orderDetailsBo.addOrderDetails(order.getOrderDetails());
            if(isOrderDetailsAdd){
                return itemBo.updateStock(order.getOrderDetails());
            }
        }
        return false;
    }

    @Override
    public void generateBill(List<CartTable> list, Map<String,Object> parameters) {
        try {

            String path = "src/main/resources/reports/invoice.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(path);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }


}
