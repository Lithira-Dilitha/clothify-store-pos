package edu.clothify.pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    private String itemCode;
    private String name;
    private String size;
    private Integer qty;
    private Double price;
    private String supplier;
    private Boolean isActive;
}
