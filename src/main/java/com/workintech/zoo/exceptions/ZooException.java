package com.workintech.zoo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ZooException extends RuntimeException {

    private HttpStatus status;

    public ZooException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return this.status;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.status = httpStatus;
    }
}
