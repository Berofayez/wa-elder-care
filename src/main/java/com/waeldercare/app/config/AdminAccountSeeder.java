package com.waeldercare.app.config;

import com.waeldercare.app.model.AdminUser;
import com.waeldercare.app.repository.AdminUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Seeds the two admin accounts from environment variables on startup, since
 * this app deliberately has no self-registration UI (Step 6/7: exactly 2
 * known admins, no public sign-up).
 */
@Component
public class AdminAccountSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminAccountSeeder.class);

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppProperties appProperties;

    public AdminAccountSeeder(AdminUserRepository adminUserRepository,
                               PasswordEncoder passwordEncoder,
                               AppProperties appProperties) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.appProperties = appProperties;
    }

    @Override
    public void run(String... args) {
        seedIfConfigured(appProperties.admin1Name(), appProperties.admin1Email(), appProperties.admin1Password());
        seedIfConfigured(appProperties.admin2Name(), appProperties.admin2Email(), appProperties.admin2Password());
    }

    private void seedIfConfigured(String name, String email, String password) {
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            return;
        }
        if (adminUserRepository.existsByEmailIgnoreCase(email)) {
            return;
        }
        AdminUser adminUser = new AdminUser();
        adminUser.setName(name == null || name.isBlank() ? email : name);
        adminUser.setEmail(email);
        adminUser.setPasswordHash(passwordEncoder.encode(password));
        adminUserRepository.save(adminUser);
        log.info("Seeded admin account for {}", email);
    }
}
