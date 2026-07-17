package com.waeldercare.app.controller;

import com.waeldercare.app.config.AppProperties;
import com.waeldercare.app.dto.ServiceRequestForm;
import com.waeldercare.app.model.CareType;
import com.waeldercare.app.model.Relationship;
import com.waeldercare.app.model.ServiceRequest;
import com.waeldercare.app.model.Urgency;
import com.waeldercare.app.repository.ServiceRequestRepository;
import com.waeldercare.app.service.EmailNotificationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicController {

    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    private final ServiceRequestRepository serviceRequestRepository;
    private final EmailNotificationService emailNotificationService;
    private final AppProperties appProperties;

    public PublicController(ServiceRequestRepository serviceRequestRepository,
                             EmailNotificationService emailNotificationService,
                             AppProperties appProperties) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.emailNotificationService = emailNotificationService;
        this.appProperties = appProperties;
    }

    @GetMapping("/")
    public String home(Model model) {
        addBusinessInfo(model);
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        addBusinessInfo(model);
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        addBusinessInfo(model);
        return "contact";
    }

    @GetMapping("/request")
    public String showRequestForm(Model model) {
        addBusinessInfo(model);
        if (!model.containsAttribute("serviceRequestForm")) {
            model.addAttribute("serviceRequestForm", new ServiceRequestForm());
        }
        model.addAttribute("careTypes", CareType.values());
        model.addAttribute("urgencies", Urgency.values());
        model.addAttribute("relationships", Relationship.values());
        return "request-form";
    }

    @PostMapping("/request")
    public String submitRequestForm(@Valid @ModelAttribute("serviceRequestForm") ServiceRequestForm form,
                                     BindingResult bindingResult,
                                     Model model) {
        if (form.isSpam()) {
            log.warn("Rejected likely-spam service request submission (honeypot filled)");
            // Pretend success to the bot; don't persist or notify.
            return "redirect:/request/confirmation";
        }

        if (bindingResult.hasErrors()) {
            addBusinessInfo(model);
            model.addAttribute("careTypes", CareType.values());
            model.addAttribute("urgencies", Urgency.values());
            model.addAttribute("relationships", Relationship.values());
            return "request-form";
        }

        ServiceRequest request = new ServiceRequest();
        request.setRequestorName(form.getRequestorName());
        request.setRequestorPhone(form.getRequestorPhone());
        request.setRequestorEmail(form.getRequestorEmail());
        request.setRelationship(form.getRelationship());
        request.setElderName(form.getElderName());
        request.setElderLocation(form.getElderLocation());
        request.setCareTypes(form.getCareTypes());
        request.setUrgency(form.getUrgency());
        request.setHowHeardAboutUs(form.getHowHeardAboutUs());
        request.setNotes(form.getNotes());

        serviceRequestRepository.save(request);
        emailNotificationService.notifyNewServiceRequest(request);

        return "redirect:/request/confirmation";
    }

    @GetMapping("/request/confirmation")
    public String confirmation(Model model) {
        addBusinessInfo(model);
        return "confirmation";
    }

    private void addBusinessInfo(Model model) {
        model.addAttribute("businessName", appProperties.businessName());
        model.addAttribute("businessPhone", appProperties.businessPhone());
    }
}
