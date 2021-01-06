package com.example.totcery_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TotResourceNotFoundException extends RuntimeException{

    public TotResourceNotFoundException(String message){
        super(message);
    }
}
