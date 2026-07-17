package com.waeldercare.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WaElderCareApplicationTests {

    @Test
    void contextLoads() {
        // Verifies the full application context (JPA entities, Spring
        // Security config, @ConfigurationProperties binding, controllers,
        // the admin account seeder) wires up without errors.
    }
}
