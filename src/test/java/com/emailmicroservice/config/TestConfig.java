package com.emailmicroservice.config;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @MockBean
    private AmazonSimpleEmailService amazonSimpleEmailService;
}