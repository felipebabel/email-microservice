package com.emailmicroservice.core.exception;

import com.emailmicroservice.application.EmailSenderService;

public class EmailServiceException extends RuntimeException{

    public EmailServiceException(final String message) {
        super(message);
    }

}
