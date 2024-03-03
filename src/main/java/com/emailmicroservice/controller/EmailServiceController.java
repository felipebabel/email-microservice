package com.emailmicroservice.controller;

import com.emailmicroservice.application.EmailSenderService;
import com.emailmicroservice.core.constant.Messages;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.util.DefaultResponse;
import com.emailmicroservice.infrastructure.amazonses.AmazonSesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailServiceController implements EmailService {

    private final EmailSenderService emailSenderService;

    private final AmazonSesConfiguration amazonSesConfiguration;

    @Autowired
    public EmailServiceController(final EmailSenderService emailSenderService,
                                  final AmazonSesConfiguration amazonSesConfiguration) {
        this.emailSenderService = emailSenderService;
        this.amazonSesConfiguration = amazonSesConfiguration;
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> sendEmail(@RequestBody EmailDto emailDto) {
        try {
            this.emailSenderService.sendEmail(emailDto);
            return new ResponseEntity<>(new DefaultResponse(DefaultResponse.SUCESS, Messages.MSG_EMAIL_SENT_SUCCESSFUL), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new DefaultResponse(DefaultResponse.ERROR, Messages.MSG_ERROR_WHEN_SEND_EMAIL), HttpStatus.BAD_REQUEST);
        }

    }

}
