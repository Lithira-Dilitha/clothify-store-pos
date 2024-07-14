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
@Table(name = "customer")
public class CustomerEntity {
    @Id
    private String customerId;
    private String name;
    private String address;
    private String email;
    private Boolean isActive;
    @OneToMany(mappedBy ="customer",cascade = CascadeType.ALL)
    private List<OrdersEntity> orders;
}
