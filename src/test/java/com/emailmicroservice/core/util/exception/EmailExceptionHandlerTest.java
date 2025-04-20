package com.emailmicroservice.core.util.exception;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException;
import com.amazonaws.services.simpleemail.model.MessageRejectedException;
import com.emailmicroservice.core.constant.Messages;
import com.emailmicroservice.core.exception.EmailExceptionHandler;
import com.emailmicroservice.core.exception.EmailServiceException;
import com.emailmicroservice.core.util.DefaultResponse;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailExceptionHandlerTest {

    private final EmailExceptionHandler handler = new EmailExceptionHandler();

    @Test
    void shouldHandleEmailServiceException() {
        EmailServiceException exception = new EmailServiceException("Email error");

        ResponseEntity<DefaultResponse> response = handler.emailExceptionHandler(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(DefaultResponse.ERROR, response.getBody().getStatus());
        assertEquals("Email error", response.getBody().getMessage());
    }

    @Test
    void shouldHandleAmazonSimpleEmailServiceException() {
        AmazonSimpleEmailServiceException ex = new AmazonSimpleEmailServiceException("AWS Error");

        ResponseEntity<DefaultResponse> response = handler.handleAmazonSimpleEmailServiceException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(DefaultResponse.ERROR, response.getBody().getStatus());
        assertEquals(Messages.MSG_INVALID_CREDENTIALS, response.getBody().getMessage());
    }

    @Test
    void shouldHandleMessageRejectedException() {
        MessageRejectedException ex = new MessageRejectedException("Rejected");

        ResponseEntity<DefaultResponse> response = handler.handleEmailVerificationError(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(DefaultResponse.ERROR, response.getBody().getStatus());
        assertEquals(Messages.MSG_INVALID_CREDENTIALS, response.getBody().getMessage());
    }

    @Test
    void shouldHandleSdkClientException() {
        SdkClientException ex = new SdkClientException("Network issue");

        ResponseEntity<DefaultResponse> response = handler.handleSdkClientException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(DefaultResponse.ERROR, response.getBody().getStatus());
        assertEquals(Messages.MSG_NETWORK, response.getBody().getMessage());
    }

}
