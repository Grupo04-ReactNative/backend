package com.group4.group4.exceptions;

public class InvalidGPTResponseException extends RuntimeException {
    public InvalidGPTResponseException(String message) {
        super(message);
    }
}
