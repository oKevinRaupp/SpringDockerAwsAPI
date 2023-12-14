package com.kevinraupp.api.studydockeraws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedMathOperationException extends RuntimeException {
    public UnsuportedMathOperationException(String string) {
        super(string);
    }
}