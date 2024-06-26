package edu.clothify.pos.dto;

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
    private String OrderId;
    private String CustomerId;
    private Date OrderDate;
    private List<OrderDetails> OrderDetails;
}
