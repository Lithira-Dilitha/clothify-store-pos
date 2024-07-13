package edu.clothify.pos.bo.orders;

import edu.clothify.pos.bo.SuperBo;
import edu.clothify.pos.dto.CartTable;
import edu.clothify.pos.dto.Orders;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;
import java.util.Map;

public interface OrdersBo extends SuperBo {
    String getOrderId();
    boolean placeOrder(Orders order);
    JasperPrint generateBill(List<CartTable> list, Map<String,Object> parameters);
}
