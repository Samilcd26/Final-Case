package com.FinalCase.helpers.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.time.LocalDate;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException implements Serializable {
private static final Long serialVersionUID=1L;


public ResourceNotFoundException(String message,String who){

    super(message);
    //logServices.CreateUser(new LogModel(who, LocalDate.now(),false));
}
}
