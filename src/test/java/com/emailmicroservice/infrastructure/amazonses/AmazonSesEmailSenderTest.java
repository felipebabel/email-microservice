package com.emailmicroservice.infrastructure.amazonses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AmazonSesEmailSenderTest {

    @Mock
    private AmazonSimpleEmailService amazonSimpleEmailService;

    @InjectMocks
    private AmazonSesEmailSender amazonSesEmailSender;

    @Test
    void shouldSendEmailUsingAmazonSES() throws EmailServiceException {
        EmailDto dto = new EmailDto();
        dto.setFrom("from@example.com");
        dto.setTo("to@example.com");
        dto.setSubject("Test Subject");
        dto.setBody("Test Body");

        amazonSesEmailSender.senderEmail(dto);

        ArgumentCaptor<SendEmailRequest> captor = ArgumentCaptor.forClass(SendEmailRequest.class);
        verify(amazonSimpleEmailService, times(1)).sendEmail(captor.capture());

        SendEmailRequest sentRequest = captor.getValue();

        assertEquals(dto.getFrom(), sentRequest.getSource());
        assertEquals(dto.getTo(), sentRequest.getDestination().getToAddresses().get(0));
        assertEquals(dto.getSubject(), sentRequest.getMessage().getSubject().getData());
        assertEquals(dto.getBody(), sentRequest.getMessage().getBody().getText().getData());
    }
}