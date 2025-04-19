package com.emailmicroservice.application;

import com.emailmicroservice.adapters.EmailSenderPdfGateway;
import com.emailmicroservice.core.EmailSenderWithPdfUseCase;
import com.emailmicroservice.core.dto.EmailBaseDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import com.emailmicroservice.core.util.ValidateFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderWithPdfService implements EmailSenderWithPdfUseCase {

    private final EmailSenderPdfGateway emailSenderPdfGateway;

    @Autowired
    public EmailSenderWithPdfService(EmailSenderPdfGateway emailSenderPdfGateway) {
        this.emailSenderPdfGateway = emailSenderPdfGateway;
    }

    @Override
    public void sendEmailWithPdf(final
                                 EmailBaseDto emailDto) throws EmailServiceException {
        ValidateFields.validate(emailDto);
        this.emailSenderPdfGateway.senderEmailWithPdf(emailDto);
    }

}
