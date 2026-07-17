package com.waeldercare.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WaElderCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaElderCareApplication.class, args);
    }
}
