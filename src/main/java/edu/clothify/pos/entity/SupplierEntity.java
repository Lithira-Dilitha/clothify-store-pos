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
@Table(name = "table")
public class SupplierEntity {
    @Id
    private String supplierId;
    private String name;
    private String company;
    private String email;
    private Boolean isActive;
}
