package com.emailmicroservice.controller;

import com.emailmicroservice.core.dto.EmailDto;
import com.emailmicroservice.core.exception.EmailServiceException;
import com.emailmicroservice.core.util.DefaultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EmailService {

    @PostMapping
    ResponseEntity<DefaultResponse> sendEmail(@RequestBody EmailDto emailDto) throws EmailServiceException;
}
