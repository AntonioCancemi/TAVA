package com.tava.auth;

import com.tava.user.Role;

import java.util.UUID;

public class AuthenticationResponse {
    private String token;
    private UUID userId;
    private UUID tenantId;
    private Role role;

    public AuthenticationResponse(String token, UUID userId, UUID tenantId, Role role) {
        this.token = token;
        this.userId = userId;
        this.tenantId = tenantId;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
