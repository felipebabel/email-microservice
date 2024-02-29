package com.emailmicroservice.adapters;

public interface EmailSenderGateway {

    void senderEmail(final String to, final String subject, final String body);

}
