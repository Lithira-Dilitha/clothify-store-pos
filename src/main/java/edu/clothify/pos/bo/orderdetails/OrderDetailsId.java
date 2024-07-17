package edu.clothify.pos.bo.orderdetails;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderDetailsId implements Serializable {
    @Column(name = "orderId")
    private String orderId;

    @Column(name = "itemCode")
    private String itemCode;
}
