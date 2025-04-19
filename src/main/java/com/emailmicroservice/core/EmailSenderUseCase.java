package com.emailmicroservice.core;

import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;

public interface EmailSenderUseCase {

    public void sendEmail(final
                          EmailDto emailDto) throws EmailServiceException;

}
