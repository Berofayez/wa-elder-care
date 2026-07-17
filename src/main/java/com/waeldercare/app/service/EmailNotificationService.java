package com.waeldercare.app.service;

import com.waeldercare.app.config.AppProperties;
import com.waeldercare.app.model.ServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.stream.Collectors;

@Service
public class EmailNotificationService {

    private static final Logger log = LoggerFactory.getLogger(EmailNotificationService.class);
    private static final DateTimeFormatter TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("MMM d, yyyy 'at' h:mm a").withZone(ZoneId.of("America/Los_Angeles"));

    private final JavaMailSender mailSender;
    private final AppProperties appProperties;

    public EmailNotificationService(JavaMailSender mailSender, AppProperties appProperties) {
        this.mailSender = mailSender;
        this.appProperties = appProperties;
    }

    /**
     * Notifies the admin inbox of a new request. Failures are logged, not
     * thrown — a broken SMTP config must never block a family's request from
     * being saved (Step 6's same-business-day promise depends on the request
     * existing in the admin dashboard even if the email never arrives).
     */
    public void notifyNewServiceRequest(ServiceRequest request) {
        String notifyEmail = appProperties.notifyEmail();
        if (notifyEmail == null || notifyEmail.isBlank()) {
            log.warn("NOTIFY_EMAIL is not configured; skipping new-request email notification");
            return;
        }

        String careTypes = request.getCareTypes().stream()
                .map(type -> type.getLabel())
                .collect(Collectors.joining(", "));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notifyEmail);
        message.setSubject("New care request: " + request.getElderName() + " (" + request.getUrgency().getLabel() + ")");
        message.setText("""
                New service request submitted %s

                Requestor: %s (%s)
                Phone: %s
                Email: %s

                Care recipient: %s
                Location: %s
                Care needed: %s
                Urgency: %s

                Notes: %s

                Reply to this request in the admin dashboard.
                """.formatted(
                TIMESTAMP_FORMAT.format(request.getCreatedAt()),
                request.getRequestorName(),
                request.getRelationship().getLabel(),
                request.getRequestorPhone(),
                request.getRequestorEmail() == null ? "(not provided)" : request.getRequestorEmail(),
                request.getElderName(),
                request.getElderLocation(),
                careTypes,
                request.getUrgency().getLabel(),
                request.getNotes() == null || request.getNotes().isBlank() ? "(none)" : request.getNotes()
        ));

        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error("Failed to send new-request notification email", e);
        }
    }
}
