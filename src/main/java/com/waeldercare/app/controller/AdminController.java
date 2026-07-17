package com.waeldercare.app.controller;

import com.waeldercare.app.config.AppProperties;
import com.waeldercare.app.model.AdminUser;
import com.waeldercare.app.model.RequestStatus;
import com.waeldercare.app.model.ServiceRequest;
import com.waeldercare.app.repository.AdminUserRepository;
import com.waeldercare.app.repository.ServiceRequestRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final ServiceRequestRepository serviceRequestRepository;
    private final AdminUserRepository adminUserRepository;
    private final AppProperties appProperties;

    public AdminController(ServiceRequestRepository serviceRequestRepository,
                            AdminUserRepository adminUserRepository,
                            AppProperties appProperties) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.adminUserRepository = adminUserRepository;
        this.appProperties = appProperties;
    }

    @GetMapping("/admin/login")
    public String loginPage(Model model) {
        model.addAttribute("businessName", appProperties.businessName());
        return "login";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        model.addAttribute("businessName", appProperties.businessName());
        model.addAttribute("requests", serviceRequestRepository.findAllByOrderByCreatedAtDesc());
        model.addAttribute("adminEmail", authentication.getName());
        return "admin/dashboard";
    }

    @GetMapping("/admin/requests/{id}")
    public String requestDetail(@PathVariable Long id, Model model, HttpServletResponse response) {
        ServiceRequest request = serviceRequestRepository.findById(id).orElse(null);
        if (request == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "admin/not-found";
        }
        model.addAttribute("businessName", appProperties.businessName());
        model.addAttribute("request", request);
        model.addAttribute("statuses", RequestStatus.values());
        return "admin/request-detail";
    }

    @PostMapping("/admin/requests/{id}/status")
    public String updateStatus(@PathVariable Long id,
                                @RequestParam RequestStatus status,
                                @RequestParam(required = false) String internalNotes,
                                Authentication authentication) {
        ServiceRequest request = serviceRequestRepository.findById(id).orElseThrow();
        request.setStatus(status);
        request.setInternalNotes(internalNotes);

        adminUserRepository.findByEmailIgnoreCase(authentication.getName())
                .ifPresent(request::setHandledBy);

        serviceRequestRepository.save(request);
        return "redirect:/admin/requests/" + id;
    }
}
