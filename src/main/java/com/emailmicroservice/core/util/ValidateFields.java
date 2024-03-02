package com.emailmicroservice.core.util;

import com.emailmicroservice.core.constant.Messages;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import java.util.Objects;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidateFields {

    public void validate(final EmailDto emailDto) throws EmailServiceException {
        if (Objects.isNull(emailDto.getSubject())) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_SUBJECT);
        }
        if (Objects.isNull(emailDto.getTo())) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_TO);
        }
        if (Objects.isNull(emailDto.getFrom())) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_FROM);
        }
        if (Objects.isNull(emailDto.getBody())) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_BODY);
        }
    }

}
