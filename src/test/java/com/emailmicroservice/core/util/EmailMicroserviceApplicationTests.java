package com.emailmicroservice.core.util;

import com.emailmicroservice.EmailMicroserviceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = EmailMicroserviceApplication.class)
class EmailMicroserviceApplicationTests {

    @Test
    void contextLoads() {
    }

}
