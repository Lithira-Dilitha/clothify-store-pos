package edu.clothify.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private String itemCode;
    private String name;
    private String size;
    private Integer qty;
    private Double price;
    private String supplier;
    private Boolean isActive;
}
