package com.emailmicroservice.controller;

import com.emailmicroservice.application.EmailSenderService;
import com.emailmicroservice.core.constant.Messages;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import com.emailmicroservice.core.util.DefaultResponse;
import com.emailmicroservice.infrastructure.amazonses.AmazonSesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailServiceAPIController implements EmailServiceAPI {

    private final EmailSenderService emailSenderService;

    private final AmazonSesConfiguration amazonSesConfiguration;

    @Autowired
    public EmailServiceAPIController(final EmailSenderService emailSenderService,
                                     final AmazonSesConfiguration amazonSesConfiguration) {
        this.emailSenderService = emailSenderService;
        this.amazonSesConfiguration = amazonSesConfiguration;
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> sendEmail(@RequestBody EmailDto emailDto) throws EmailServiceException {
        this.emailSenderService.sendEmail(emailDto);
        return new ResponseEntity<>(new DefaultResponse(DefaultResponse.SUCESS, Messages.MSG_EMAIL_SENT_SUCCESSFUL), HttpStatus.OK);
    }

}
