package com.FinalCase.business.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class UserDto implements Serializable {
    private long idNumber;
    private String firstName;
    private String lastName;
    private long monthlySalary;
    private long telNumber;
    private String email;
    private LocalDate birthDate;
    private Long guaranteeAmount;
    private Long creditScore;
    private Long creditLimit;
    private boolean creditStatus;
}
