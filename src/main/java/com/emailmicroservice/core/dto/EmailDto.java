package com.emailmicroservice.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDto {

    @Schema(description = "Recipient email address", example = "recipient@example.com")
    private String to;

    @Schema(description = "Email subject", example = "Hive a nice day!")
    private String subject;

    @Schema(description = "Email body", example = "Hello, what's your favorite food?")
    private String body;

    @Schema(description = "Sender email address", example = "sender@example.com")
    private String from;

}
