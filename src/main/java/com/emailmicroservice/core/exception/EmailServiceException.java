package com.emailmicroservice.core.exception;

public class EmailServiceException extends Exception {
    public EmailServiceException(final String message,
                                 final Object... args) {
        super(String.format(message, args));
    }

}
