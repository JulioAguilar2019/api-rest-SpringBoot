package com.app.apispringboot.exceptions;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public BlogAppException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
    public BlogAppException(HttpStatus status, String message, String message1) {
        super();
        this.status = status;
        this.message = message;
        this.message = message1;
    }


}
