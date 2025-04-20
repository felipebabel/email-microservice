package com.emailmicroservice.core.util.exception;

import com.emailmicroservice.core.constant.Messages;
import com.emailmicroservice.core.dto.EmailBaseDto;
import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import com.emailmicroservice.core.util.ValidateFields;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ValidateFieldsTest {

    @Test
    void shouldValidateEmailDtoSuccessfully() {
        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("Test Subject");
        emailDto.setTo("to@example.com");
        emailDto.setFrom("from@example.com");
        emailDto.setBody("Test Body");

        assertDoesNotThrow(() -> ValidateFields.validate(emailDto));
    }

    @Test
    void shouldThrowWhenEmailDtoBodyIsNull() {
        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("Test Subject");
        emailDto.setTo("to@example.com");
        emailDto.setFrom("from@example.com");
        emailDto.setBody(null);

        EmailServiceException exception = assertThrows(EmailServiceException.class,
                () -> ValidateFields.validate(emailDto));

        String expectedMessage = String.format(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_BODY);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldValidateEmailBaseDtoSuccessfully() {
        EmailBaseDto emailDto = new EmailDto();
        emailDto.setSubject("Test Subject");
        emailDto.setTo("to@example.com");
        emailDto.setFrom("from@example.com");

        assertDoesNotThrow(() -> ValidateFields.validate(emailDto));
    }

    @Test
    void shouldThrowWhenSubjectIsNull() {
        EmailDto emailDto = new EmailDto();
        emailDto.setSubject(null);
        emailDto.setTo("to@example.com");
        emailDto.setFrom("from@example.com");
        emailDto.setBody("Test Body");

        EmailServiceException exception = assertThrows(EmailServiceException.class,
                () -> ValidateFields.validate(emailDto));

        String expectedMessage = String.format(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_SUBJECT);
        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void shouldThrowWhenToIsNull() {
        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("Subject");
        emailDto.setTo(null);
        emailDto.setFrom("from@example.com");
        emailDto.setBody("Test Body");

        EmailServiceException exception = assertThrows(EmailServiceException.class,
                () -> ValidateFields.validate(emailDto));

        String expectedMessage = String.format(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_TO);
        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void shouldThrowWhenFromIsNull() {
        EmailDto emailDto = new EmailDto();
        emailDto.setSubject("Subject");
        emailDto.setTo("to@example.com");
        emailDto.setFrom(null);
        emailDto.setBody("Test Body");

        EmailServiceException exception = assertThrows(EmailServiceException.class,
                () -> ValidateFields.validate(emailDto));

        String expectedMessage = String.format(Messages.MSG_FIELD_ID_NOT_INFORMED, Messages.FIELD_FROM);
        assertEquals(expectedMessage, exception.getMessage());

    }
}
