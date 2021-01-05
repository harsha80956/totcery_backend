package com.example.totcery_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class totAuthException extends RuntimeException{

    public totAuthException(String message){
        super(message);
    }

}
