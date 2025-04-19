package com.emailmicroservice.infrastructure.amazonses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.emailmicroservice.adapters.EmailSenderGateway;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmazonSesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public AmazonSesEmailSender(final AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void senderEmail(final
                            EmailDto emailDto) throws EmailServiceException {
        final SendEmailRequest request = new SendEmailRequest();
        request.withSource(emailDto.getFrom())
            .withDestination(new Destination().withToAddresses(emailDto.getTo()))
            .withMessage(new Message().withBody(new Body().withText(new Content(emailDto.getBody())))
                .withSubject(new Content(emailDto.getSubject())));
        this.amazonSimpleEmailService.sendEmail(request);
    }

}
