package com.emailmicroservice.application;

import com.emailmicroservice.adapters.EmailSenderGateway;
import com.emailmicroservice.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderUseCase {

    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public EmailSenderService(final EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(final String to,
                          final String subject,
                          final String body) {
        this.emailSenderGateway.senderEmail(to, subject, body);
    }

}
