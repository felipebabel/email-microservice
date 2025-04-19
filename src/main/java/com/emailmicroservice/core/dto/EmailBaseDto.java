package com.emailmicroservice.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailBaseDto {

    @Schema(description = "Recipient email address", example = "recipient@example.com")
    private String to;

    @Schema(description = "Email subject", example = "Hive a nice day!")
    private String subject;

    @Schema(description = "Sender email address", example = "sender@example.com")
    private String from;

}
