package com.eomaxl.springbootblog.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogApiException(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public BlogApiException(HttpStatus status, String message, String messag1) {
        super(message);
        this.status = status;
        this.message = messag1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
