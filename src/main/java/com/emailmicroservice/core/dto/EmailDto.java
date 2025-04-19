package com.emailmicroservice.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDto extends EmailBaseDto {

    @Schema(description = "Email body", example = "Hello, what's your favorite food?")
    private String body;

}
