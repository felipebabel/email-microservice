package com.emailmicroservice.adapters;

import com.emailmicroservice.core.dto.EmailBaseDto;
import com.emailmicroservice.core.exception.EmailServiceException;

public interface EmailSenderPdfGateway {

    void senderEmailWithPdf(final
                            EmailBaseDto emailDto) throws EmailServiceException;

}
