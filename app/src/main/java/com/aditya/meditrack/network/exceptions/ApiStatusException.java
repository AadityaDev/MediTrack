package com.aditya.meditrack.network.exceptions;

public class ApiStatusException extends HttpException {
    public ApiStatusException(String message) {
        super(message);
    }
}
