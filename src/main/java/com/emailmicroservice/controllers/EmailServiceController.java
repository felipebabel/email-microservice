package com.emailmicroservice.controllers;

import com.emailmicroservice.application.EmailSenderService;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import com.emailmicroservice.infrastructure.amazonses.AmazonSesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailServiceController {

    private final EmailSenderService emailSenderService;

    private final AmazonSesConfiguration amazonSesConfiguration;

    @Autowired
    public EmailServiceController(final EmailSenderService emailSenderService,
                                  final AmazonSesConfiguration amazonSesConfiguration) {
        this.emailSenderService = emailSenderService;
        this.amazonSesConfiguration = amazonSesConfiguration;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto) throws EmailServiceException {
        this.emailSenderService.sendEmail(emailDto);
        return new ResponseEntity<>("Email sent successful", HttpStatus.OK);
    }

}
