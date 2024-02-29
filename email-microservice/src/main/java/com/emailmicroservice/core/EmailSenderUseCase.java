package com.emailmicroservice.core;

public interface EmailSenderUseCase {

    public void sendEmail(final String to,
                          final String subject,
                          final String body);

}
