package com.emailmicroservice.core;

import com.emailmicroservice.core.dto.EmailBaseDto;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;

public interface EmailSenderWithPdfUseCase {

    public void sendEmailWithPdf(final
                                 EmailBaseDto emailDto) throws EmailServiceException;

}
