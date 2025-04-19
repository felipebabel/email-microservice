package com.emailmicroservice.core.util;

import com.emailmicroservice.core.constant.Messages;
import com.emailmicroservice.core.dto.EmailBaseDto;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
public class ValidateFields {

    static final Logger LOGGER = LoggerFactory.getLogger("ValidateFields");
    public static final String VALIDATING_REQUIRED_FIELD = "Validating required field";

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

    private static void baseValidate(String emailDto, String emailDto1, String emailDto2) throws EmailServiceException {
        if (Objects.isNull(emailDto)) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_SUBJECT);
        }
        if (Objects.isNull(emailDto1)) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_TO);
        }
        if (Objects.isNull(emailDto2)) {
            throw new EmailServiceException(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_FROM);
        }
    }


}
