package com.emailmicroservice.infrastructure.amazonses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.emailmicroservice.adapters.EmailSenderGateway;
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
    public void senderEmail(final String to,
                            final String subject,
                            final String body) {
        final SendEmailRequest request = new SendEmailRequest();
        request.withSource("babelfelipe@gmail.com")
            .withDestination(new Destination().withToAddresses(to))
            .withMessage(new Message().withBody(new Body().withText(new Content(body)))
                .withSubject(new Content(subject)));
        try {
            this.amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonServiceException e) {
            throw new EmailServiceException("Error when send email");
        }
    }

}
