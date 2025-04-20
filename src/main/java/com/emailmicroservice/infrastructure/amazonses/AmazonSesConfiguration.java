package com.emailmicroservice.infrastructure.amazonses;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonSesConfiguration {

    private final String awsAccessKeyId;
    private final String awsSecretKey;
    private final String awsRegion;

    public AmazonSesConfiguration(@Value("${aws.accessKeyId}") String awsAccessKeyId,
                                  @Value("${aws.secretKey}") String awsSecretKey,
                                  @Value("${aws.region}") String awsRegion) {
        this.awsAccessKeyId = awsAccessKeyId;
        this.awsSecretKey = awsSecretKey;
        this.awsRegion = awsRegion;
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKeyId,
                        awsSecretKey)))
                .withRegion(awsRegion).build();
    }

}
