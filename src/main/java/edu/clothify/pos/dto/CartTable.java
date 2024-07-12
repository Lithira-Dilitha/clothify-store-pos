package edu.clothify.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTable {
    private String itemCode;
    private String desc;
    private Integer qty;
    private String size;
    private Double uniPrice;
    private Double total;
}
