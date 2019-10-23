package com.nab.interview.exception;

public class ServiceException extends RuntimeException {


    public ServiceException(String errorMessage) {
        super(errorMessage);
    }
}

