package com.example.totcery_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TotBadRequestException extends RuntimeException {

    public TotBadRequestException(String message){
        super(message);
    }

}
