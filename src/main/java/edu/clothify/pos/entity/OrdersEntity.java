package edu.clothify.pos.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "Orders")
public class OrdersEntity {
    @Id
    private String orderId;
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name ="customerId")
    private CustomerEntity customer;
    private Double total;
    @ManyToOne
    @JoinColumn(name ="userId")
    private UserEntity user;

}
