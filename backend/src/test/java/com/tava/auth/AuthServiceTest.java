package com.tava.auth;

import com.tava.tenant.TenantService;
import com.tava.user.Role;
import com.tava.user.User;
import com.tava.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

public class AuthServiceTest {

    @Test
    void testRegisterTenant() {
        TenantService tenantService = mock(TenantService.class);
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);
        JwtUtils jwtUtils = new JwtUtils("0123456789012345678901234567890123456789012345678901234567890123", 1000);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserDetailsService userDetailsService = mock(UserDetailsService.class);

        AuthService authService = new AuthService(tenantService, userRepository, encoder, jwtUtils, authenticationManager, userDetailsService);

        User admin = new User();
        admin.setId(UUID.randomUUID());
        admin.setTenantId(UUID.randomUUID());
        admin.setRole(Role.ADMIN);

        when(tenantService.createTenantWithAdmin(any(), any(), any())).thenReturn(admin);

        RegisterTenantRequest request = new RegisterTenantRequest();
        request.setTenantName("test");
        request.setAdminEmail("a@b.com");
        request.setPassword("pass");

        AuthenticationResponse response = authService.registerTenant(request);
        assertNotNull(response.getToken());
        assertEquals(admin.getTenantId(), response.getTenantId());
    }

    @Test
    void testLogin() {
        TenantService tenantService = mock(TenantService.class);
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);
        JwtUtils jwtUtils = new JwtUtils("0123456789012345678901234567890123456789012345678901234567890123", 1000);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserDetailsService userDetailsService = mock(UserDetailsService.class);

        AuthService authService = new AuthService(tenantService, userRepository, encoder, jwtUtils, authenticationManager, userDetailsService);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setTenantId(UUID.randomUUID());
        user.setRole(Role.ADMIN);
        user.setEmail("test@test.com");

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()).password("pass").roles("ADMIN").build();

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(userDetails);
        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(userDetailsService.loadUserByUsername(any())).thenReturn(userDetails);
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));

        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("test@test.com");
        request.setPassword("pass");

        AuthenticationResponse response = authService.login(request);
        assertEquals(user.getTenantId(), response.getTenantId());
    }
}
