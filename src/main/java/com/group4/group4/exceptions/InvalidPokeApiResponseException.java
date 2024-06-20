package com.group4.group4.exceptions;

public class InvalidPokeApiResponseException extends RuntimeException {
    public InvalidPokeApiResponseException(String message) {
        super(message);
    }
}
