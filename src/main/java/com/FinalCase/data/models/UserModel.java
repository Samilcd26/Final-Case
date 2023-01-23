package com.FinalCase.data.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name="users")
public class UserModel implements Serializable {

    public UserModel(long idNumber, String firstName, String lastName, long monthlySalary, long telNumber, String email, Date birthDate, Long guaranteeAmount, Long creditScore, Long creditLimit, boolean creditStatus) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.monthlySalary = monthlySalary;
        this.telNumber = telNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.guaranteeAmount = guaranteeAmount ;
        this.creditScore = creditScore;
        this.creditLimit = creditLimit;
        this.creditStatus = creditStatus;
    }

    @Id
    @Column(name = "id_number")
    private long idNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "monthly_salary")
    private long monthlySalary;

    @Column(name = "tel_number")
    private long telNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "guarantee_amount")
    private Long guaranteeAmount;

    @Column(name = "credit_score")
    private Long creditScore;

    @Column(name = "credit_limit")
    private Long creditLimit;

    @Column(name = "credit_status")
    private boolean creditStatus;

}
