package com.emailmicroservice.infrastructure.amazonses.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI apiDoc() {
        return new OpenAPI()
            .info(new Info()
                .title("Email Microsservice")
                .version("v1")
                .description("REST API Email microsservice")
            );
    }

}
