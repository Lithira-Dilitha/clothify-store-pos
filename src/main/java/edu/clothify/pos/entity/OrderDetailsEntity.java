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
@Table(name = "orderDetails")
public class OrderDetailsEntity {
    @Id
    private String OrderId;
    private String ItemCode;
    private Integer Qty;
}
