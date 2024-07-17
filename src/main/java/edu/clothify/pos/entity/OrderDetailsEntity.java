package edu.clothify.pos.entity;

import edu.clothify.pos.bo.orderdetails.OrderDetailsId;
import jakarta.persistence.*;
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
    @EmbeddedId
    private OrderDetailsId id;
    private Integer qty;
}
