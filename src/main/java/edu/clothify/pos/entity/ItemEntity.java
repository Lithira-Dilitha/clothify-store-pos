package edu.clothify.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemEntity {
    private String itemCode;
    private String name;
    private String size;
    private Integer qty;
    private Double price;
}
