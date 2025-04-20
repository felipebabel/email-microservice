package com.emailmicroservice.infrastructure.amazonses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.emailmicroservice.core.dto.EmailBaseDto;
import com.emailmicroservice.core.exception.EmailServiceException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailSenderPdfGatewayImplTest {

    @Mock
    private AmazonSimpleEmailService amazonSimpleEmailService;

    @InjectMocks
    private AmazonSesEmailSenderWithPdf emailSenderPdfGateway;

    @Test
    void shouldSendEmailWithPdfSuccessfully() throws Exception {
        EmailBaseDto dto = new EmailBaseDto();
        dto.setFrom("from@example.com");
        dto.setTo("to@example.com");
        dto.setSubject("Test PDF Email");

        emailSenderPdfGateway.senderEmailWithPdf(dto);

        ArgumentCaptor<SendRawEmailRequest> captor = ArgumentCaptor.forClass(SendRawEmailRequest.class);
        verify(amazonSimpleEmailService, times(1)).sendRawEmail(captor.capture());

        SendRawEmailRequest request = captor.getValue();
        assertNotNull(request.getRawMessage());
        assertNotNull(request.getRawMessage().getData());
        assertTrue(request.getRawMessage().getData().remaining() > 0);
    }

    @Test
    void shouldThrowEmailServiceExceptionWhenSomethingGoesWrong() {
        EmailBaseDto dto = new EmailBaseDto();
        dto.setFrom(null);
        dto.setTo("to@example.com");
        dto.setSubject("Subject");

        EmailServiceException exception = assertThrows(EmailServiceException.class,
                () -> emailSenderPdfGateway.senderEmailWithPdf(dto));

        assertTrue(exception.getMessage().contains("Error while sending email with PDF"));
    }
}