package edu.clothify.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierEntity {
    private String supplierId;
    private String name;
    private String company;
    private String email;
}
