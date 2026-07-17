package com.waeldercare.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record AppProperties(
        String notifyEmail,
        String businessName,
        String businessPhone,
        String admin1Name,
        String admin1Email,
        String admin1Password,
        String admin2Name,
        String admin2Email,
        String admin2Password) {
}
