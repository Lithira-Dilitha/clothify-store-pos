package edu.clothify.pos.dto;

import edu.clothify.pos.entity.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders {
    private String orderId;
    private Date orderDate;
    private String customerId;
    private List<OrderDetails> orderDetailsList;
    private String userId;
}
