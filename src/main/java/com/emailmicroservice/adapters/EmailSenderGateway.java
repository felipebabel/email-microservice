package com.emailmicroservice.adapters;

import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;

public interface EmailSenderGateway {

    void senderEmail(final
                     EmailDto emailDto) throws EmailServiceException;
}
