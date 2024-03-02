package com.emailmicroservice.application;

import com.emailmicroservice.adapters.EmailSenderGateway;
import com.emailmicroservice.core.EmailSenderUseCase;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import com.emailmicroservice.core.util.ValidateFields;
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
    public void sendEmail(final
                          EmailDto emailDto) throws EmailServiceException {
        ValidateFields.validate(emailDto);
        this.emailSenderGateway.senderEmail(emailDto);
    }

}
