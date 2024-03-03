package com.emailmicroservice.core.exception;

import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException;
import com.amazonaws.services.simpleemail.model.MessageRejectedException;
import com.emailmicroservice.core.constant.Messages;
import com.emailmicroservice.core.util.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EmailExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailServiceException.class)
    private ResponseEntity<DefaultResponse> emailExceptionHandler(final EmailServiceException exception) {
        return new ResponseEntity<>(new DefaultResponse(DefaultResponse.ERROR, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AmazonSimpleEmailServiceException.class)
    public ResponseEntity<DefaultResponse> handleAmazonSimpleEmailServiceException(final AmazonSimpleEmailServiceException ex) {
        return new ResponseEntity<>(new DefaultResponse(DefaultResponse.ERROR, Messages.MSG_INVALID_CREDENTIALS), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MessageRejectedException.class)
    public ResponseEntity<DefaultResponse> handleEmailVerificationError(final MessageRejectedException ex) {
        return new ResponseEntity<>(new DefaultResponse(DefaultResponse.ERROR, Messages.MSG_INVALID_CREDENTIALS), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
