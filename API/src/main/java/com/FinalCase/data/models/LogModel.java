package com.FinalCase.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;



@Document("log")
public class LogModel implements Serializable {


    public LogModel(String processType, LocalDate processDate, Boolean processResult) {
        this.processType = processType;
        this.processDate = processDate;
        this.processResult = processResult;
    }

    private String processType;
    private LocalDate processDate;
    private Boolean processResult;
    private String description;

}
