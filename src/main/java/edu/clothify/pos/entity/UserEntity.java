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
@Table(name = "users")
public class UserEntity {
    @Id
    private String userId;
    private String name;
    private String role;
    private String email;
    private String password;
    private Boolean isActive;
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL)
    private List<OrdersEntity> orders;
}
