package com.emailmicroservice.controller;

import com.emailmicroservice.application.EmailSenderService;
import com.emailmicroservice.application.EmailSenderWithPdfService;
import com.emailmicroservice.core.constant.Messages;
import com.emailmicroservice.core.dto.EmailBaseDto;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import com.emailmicroservice.core.util.DefaultResponse;
import com.emailmicroservice.infrastructure.amazonses.AmazonSesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/email")
public class EmailServiceApiController implements EmailServiceApi {

    static final Logger LOGGER = LoggerFactory.getLogger("EmailServiceAPIController");
    private final EmailSenderService emailSenderService;
    private final EmailSenderWithPdfService emailSenderWithPdfService;
    private final AmazonSesConfiguration amazonSesConfiguration;


    @Autowired
    public EmailServiceApiController(final EmailSenderService emailSenderService,
                                     EmailSenderWithPdfService emailSenderWithPdfService,
                                     final AmazonSesConfiguration amazonSesConfiguration) {
        this.emailSenderService = emailSenderService;
        this.emailSenderWithPdfService = emailSenderWithPdfService;
        this.amazonSesConfiguration = amazonSesConfiguration;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> sendEmail(@RequestBody EmailDto emailDto)
            throws EmailServiceException {
        LOGGER.info("Sending email");
        this.emailSenderService.sendEmail(emailDto);
        LOGGER.info("Sent email");
        return new ResponseEntity<>(new DefaultResponse(DefaultResponse.SUCESS,
                Messages.MSG_EMAIL_SENT_SUCCESSFUL), HttpStatus.OK);
    }

    @PostMapping(
            value = "/pdf",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> sendEmailWithPdf(@RequestBody EmailBaseDto emailBaseDto) throws
            EmailServiceException {
        LOGGER.info("Sending email with PDF");
        this.emailSenderWithPdfService.sendEmailWithPdf(emailBaseDto);
        LOGGER.info("Sent email");
        return new ResponseEntity<>(new DefaultResponse(DefaultResponse.SUCESS,
                Messages.MSG_EMAIL_SENT_SUCCESSFUL), HttpStatus.OK);
    }

}
