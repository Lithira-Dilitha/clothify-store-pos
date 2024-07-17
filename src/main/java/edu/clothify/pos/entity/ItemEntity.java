package edu.clothify.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String supplierId;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "supplierId", nullable = false,insertable=false, updatable=false)
    private SupplierEntity supplier;

}
