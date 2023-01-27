package com.FinalCase.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Data
@NoArgsConstructor

@Entity
@Table(name="queue")
public class QueueModel {

    @Id
    @GeneratedValue
    @Column(name = "queueId")
    private long queueId;



    @Column(name = "id_number")
    private long idNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;



    public QueueModel(long queueId, long idNumber, LocalDate birthDate, String status) {
        this.queueId = queueId;
        this.idNumber = idNumber;
        this.birthDate = birthDate;
        this.status = status;
    }

    @Column(name = "status")
    private String status;
}
