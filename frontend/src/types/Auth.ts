export interface User {
  id: string;
  email: string;
  tenantId: string;
  role: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  user: User;
}

export interface RegisterTenantRequest {
  tenantName: string;
  email: string;
  password: string;
}

export interface RegisterTenantResponse {
  token: string;
  user: User;
}
