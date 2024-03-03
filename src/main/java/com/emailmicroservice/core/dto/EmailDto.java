package com.emailmicroservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDto {

    @ApiModelProperty(value = "Recipient email address", example = "recipient@example.com")
    private String to;

    @ApiModelProperty(value = "Email subject", example = "Hive a nice day!")
    private String subject;

    @ApiModelProperty(value = "Email body", example = "Hello, what's your favorite food?")
    private String body;

    @ApiModelProperty(value = "Sender email address", example = "sender@example.com")
    private String from;

}
