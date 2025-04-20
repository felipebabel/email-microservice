package com.emailmicroservice.core.util;

import java.util.Objects;
import lombok.experimental.UtilityClass;
import com.emailmicroservice.core.constant.Messages;
import com.emailmicroservice.core.dto.EmailBaseDto;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
public class ValidateFields {

    public static final String VALIDATING_REQUIRED_FIELD = "Validating required field";
    static final Logger LOGGER = LoggerFactory.getLogger("ValidateFields");

    public void validate(final EmailDto emailDto) throws EmailServiceException {
        LOGGER.info(VALIDATING_REQUIRED_FIELD);
        baseValidate(emailDto.getSubject(), emailDto.getTo(), emailDto.getFrom());
        if (Objects.isNull(emailDto.getBody())) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_BODY);
        }
    }

    public void validate(final EmailBaseDto emailDto) throws EmailServiceException {
        LOGGER.info(VALIDATING_REQUIRED_FIELD);
        baseValidate(emailDto.getSubject(), emailDto.getTo(), emailDto.getFrom());
    }

    private static void baseValidate(String subject, String to, String from) throws EmailServiceException {
        if (Objects.isNull(subject)) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_SUBJECT);
        }
        if (Objects.isNull(to)) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_TO);
        }
        if (Objects.isNull(from)) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_FROM);
        }
    }


}
