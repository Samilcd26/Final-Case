package com.FinalCase.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
@Table(name = "employees")
public class EmployeeModel {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    public EmployeeModel(Long id, String employeeName, String employeePassword, String role, String email) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
        this.role = role;
        this.email = email;
    }

    private String employeeName;

    private String employeePassword;
    private String role;
    private String email;

}
