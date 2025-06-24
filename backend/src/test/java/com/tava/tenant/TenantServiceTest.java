package com.tava.tenant;

import com.tava.user.Role;
import com.tava.user.User;
import com.tava.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TenantServiceTest {

    @Test
    void testCreateTenantWithAdmin() {
        TenantRepository tenantRepository = mock(TenantRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);

        TenantService tenantService = new TenantService(tenantRepository, userRepository, encoder);

        Tenant tenant = new Tenant();
        tenant.setId(UUID.randomUUID());
        tenant.setName("test");
        when(tenantRepository.save(any())).thenReturn(tenant);

        User adminSaved = new User();
        when(userRepository.save(any())).thenReturn(adminSaved);

        User admin = tenantService.createTenantWithAdmin("test", "mail@test.com", "pass");
        assertNotNull(admin);
        verify(tenantRepository).save(any());
        verify(userRepository).save(any());
    }
}
