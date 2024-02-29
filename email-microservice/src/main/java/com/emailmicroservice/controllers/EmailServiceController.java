package com.emailmicroservice.controllers;

import com.emailmicroservice.application.EmailSenderService;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/email")
public class EmailServiceController {

    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailServiceController(final EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto) {
        try {
            this.emailSenderService.sendEmail(emailDto.getTo(), emailDto.getSubject(), emailDto.getBody());
            return ResponseEntity.ok("Email sent successful");
        } catch (EmailServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error sending email");
        }
    }

}
