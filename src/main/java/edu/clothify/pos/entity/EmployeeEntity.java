package edu.clothify.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeEntity {
    private String employeeId;
    private String name;
    private String address;
    private String email;
    private Boolean isActive;
}
