package com.tava.auth;

import com.tava.tenant.TenantService;
import com.tava.user.User;
import com.tava.user.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final TenantService tenantService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthService(TenantService tenantService,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtils jwtUtils,
                       AuthenticationManager authenticationManager,
                       UserDetailsService userDetailsService) {
        this.tenantService = tenantService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public AuthenticationResponse registerTenant(RegisterTenantRequest request) {
        User admin = tenantService.createTenantWithAdmin(
                request.getTenantName(),
                request.getAdminEmail(),
                request.getPassword()
        );
        String token = jwtUtils.generateToken(admin.getId(), admin.getTenantId(), admin.getRole());
        return new AuthenticationResponse(token, admin.getId(), admin.getTenantId(), admin.getRole());
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        String token = jwtUtils.generateToken(user.getId(), user.getTenantId(), user.getRole());
        return new AuthenticationResponse(token, user.getId(), user.getTenantId(), user.getRole());
    }

    public Claims parseToken(String token) {
        return jwtUtils.parseToken(token);
    }
}
