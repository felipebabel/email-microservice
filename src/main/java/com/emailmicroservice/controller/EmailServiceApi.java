package com.emailmicroservice.controller;

import com.emailmicroservice.core.constant.Messages;
import com.emailmicroservice.core.dto.EmailBaseDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import com.emailmicroservice.core.util.DefaultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import com.emailmicroservice.core.dto.EmailDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface EmailServiceApi {

    @Operation(
        summary = "Send an email",
        description = "This endpoint sends an email using the provided recipient, subject, body. Ensure all required fields are provided for successful email delivery.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = Messages.MSG_EMAIL_SENT_SUCCESSFUL,
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        Messages.SUCCESS_MESSAGE))),
            @ApiResponse(
                responseCode = "400",
                content = @Content(
                    mediaType = "application/json",
                    examples = {
                        @ExampleObject(
                            name = "Status 1, Error",
                            value = Messages.ERROR_MESSAGE),
                        @ExampleObject(
                            name = "Status 2, Error",
                            value = Messages.ERROR_FIELD_ID_NOT_INFORMED)})),
            @ApiResponse(
                responseCode = "500",
                content = @Content(
                    mediaType = "application/json",
                    examples = {
                        @ExampleObject(
                            name = "Status 1, Error",
                            value = Messages.ERROR_INVALID_CREDENTIALS),
                        @ExampleObject(
                            name = "Status 2, Error",
                            value = Messages.ERROR_NETWORK)
                    }))
        })
    ResponseEntity<DefaultResponse> sendEmail(@RequestBody EmailDto emailDto) throws EmailServiceException;

    @Operation(
            summary = "Send an email with random PDF",
            description = "This endpoint sends an email using the provided recipient, subject and a random PDF. Ensure all required fields are provided for successful email delivery.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = Messages.MSG_EMAIL_SENT_SUCCESSFUL,
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            Messages.SUCCESS_MESSAGE))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Status 1, Error",
                                                    value = Messages.ERROR_MESSAGE),
                                            @ExampleObject(
                                                    name = "Status 2, Error",
                                                    value = Messages.ERROR_FIELD_ID_NOT_INFORMED)})),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    name = "Status 1, Error",
                                                    value = Messages.ERROR_INVALID_CREDENTIALS),
                                            @ExampleObject(
                                                    name = "Status 2, Error",
                                                    value = Messages.ERROR_NETWORK)
                                    }))
            })
    ResponseEntity<DefaultResponse> sendEmailWithPdf(@RequestBody EmailBaseDto emailDto) throws EmailServiceException;

}
