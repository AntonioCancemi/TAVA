export interface User {
  id: string;
  email: string;
  tenant: string;
  [key: string]: unknown;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  user: User;
  token: string;
}

export interface RegisterTenantRequest {
  tenantName: string;
  email: string;
  password: string;
}

export interface RegisterTenantResponse {
  user: User;
  token: string;
}
