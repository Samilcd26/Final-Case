package com.FinalCase.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Log4j2

public class MessageModel {



    private long idNumber;
    private String firstName;
    private String lastName;
    private String email;
    private Long creditLimit;
    private boolean creditStatus;
}
