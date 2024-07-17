package edu.clothify.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Supplier")
public class SupplierEntity {
    @Id
    private String supplierId;
    private String name;
    private String company;
    private String email;
    private Boolean isActive;

    @OneToMany(mappedBy ="supplier",cascade = CascadeType.ALL)
    private List<ItemEntity> item;
}
