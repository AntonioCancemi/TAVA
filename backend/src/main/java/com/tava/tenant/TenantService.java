package com.tava.tenant;

import com.tava.user.Role;
import com.tava.user.User;
import com.tava.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TenantService(TenantRepository tenantRepository,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder) {
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createTenantWithAdmin(String tenantName, String email, String password) {
        Tenant tenant = new Tenant();
        tenant.setName(tenantName);
        tenant = tenantRepository.save(tenant);

        User admin = new User();
        admin.setEmail(email);
        admin.setUsername(email);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRole(Role.ADMIN);
        admin.setTenantId(tenant.getId());
        return userRepository.save(admin);
    }

    public Tenant findById(UUID id) {
        return tenantRepository.findById(id).orElse(null);
    }
}
